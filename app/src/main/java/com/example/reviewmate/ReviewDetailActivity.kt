package com.example.reviewmate

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.reviewmate.MyApplication.Companion.auth
import com.example.reviewmate.MyApplication.Companion.db
import com.example.reviewmate.databinding.ActivityReviewDetailBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ReviewDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityReviewDetailBinding
    private var myName = MyApplication.email
    lateinit var itemList: MutableList<ItemCommentModel>
    lateinit var itemListF: MutableList<ItemFeedModel>
    private lateinit var adapter: MyCommentAdapter
    private lateinit var adapterF: MyFeedAdapter
    lateinit var reviewId: String

    lateinit var file: File
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding을 사용하여 레이아웃 파일 설정
        binding =ActivityReviewDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var toolbar = binding.toolbarBack
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayShowTitleEnabled(false)//타이틀 없애기

        // ------------- API : 영화 제목, 포스터 추가해야 함 --------------
        binding.movieTitle.text = intent.getStringExtra("movie")
        binding.movieRate.text = intent.getStringExtra("rate")
        binding.reviewTitle.text = intent.getStringExtra("title")
        binding.userEmail.text = intent.getStringExtra("userEmail")
        binding.reviewDate.text = intent.getStringExtra("date")
        binding.reviewId.text = intent.getStringExtra("reviewId")

        reviewId = intent.getStringExtra("reviewId").toString()
        Log.d("get테스트", "" + reviewId)
        // 영화 API 사용하여 데이터 가져와야 함

        setProfileImage()

        val movieImage = intent.getStringExtra("movieImage")
        if (movieImage != null && movieImage != "null") {
            // Glide를 사용하여 프로필 이미지 로드
            Glide.with(baseContext)
                .load(movieImage)
                .into(binding.addImageView)
        }

        // Firestore에서 데이터 가져오기
        val db = FirebaseFirestore.getInstance()
        val reviewRef = db.collection("reviews").document(reviewId)

        reviewRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val content = document.getString("content")
                    if (content != null) {
                        binding.content.text = content
                    }
                } else {
                    // 문서가 없는 경우 처리
                }
            }
            .addOnFailureListener { exception ->
                // 데이터 가져오기 실패 처리
            }

        val alertHandler = DialogInterface.OnClickListener { dialog, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    if (reviewId !== null) {
                        MyApplication.storage.getReference().child("images")
                            .child("${reviewId}.jpg")
                            .delete()
                        db.collection("reviews").document("${reviewId}")
                            .delete()
                            .addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "삭제가 완료되었습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val userDocRef = MyApplication.db.collection("users")
                                    .document(auth.uid.toString())
                                MyApplication.db.collection("users")
                                    .document("${MyApplication.auth.uid}")
                                    .get()
                                    .addOnSuccessListener { documentSnapshot ->
                                        if (documentSnapshot.exists()) {
                                            val currentCount =
                                                documentSnapshot.getLong("userReviewCount")
                                            currentCount?.let {
                                                val updatedCount = it - 1
                                                updateCount(userDocRef, updatedCount)
                                                onBackPressed()
                                            }
                                        }
                                    }
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    this,
                                    "삭제가 실패하였습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    } else {
                        Toast.makeText(this, "문서가 존재하지 않습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    Log.d("ToyProject", "DialogInterface.BUTTON_NEGATIVE")
                }
            }
            dialog.dismiss() // 다이얼로그를 닫습니다.
        }

        if(MyApplication.checkAuth()){
            if(binding.userEmail.text == MyApplication.email){
                binding.menuDelete.visibility = View.VISIBLE
                binding.menuUpdate.visibility = View.VISIBLE

                binding.menuUpdate.setOnClickListener {
                    intent = Intent(this, UpdateActivity::class.java)
                    intent.putExtra("reviewId", reviewId)
                    startActivity(intent)
                }
                binding.menuDelete.setOnClickListener {
                    AlertDialog.Builder(this).run {
                        setTitle("정말 삭제하시겠습니까?")
                        setMessage("한 번 삭제하면 되돌릴 수 없습니다.")
                        setNegativeButton("Cancle", alertHandler)
                        setPositiveButton("Yes", alertHandler)
                        show()
                    }
                }
            }
            else{
                binding.menuDelete.visibility = View.INVISIBLE
                binding.menuUpdate.visibility = View.INVISIBLE
            }
        }

        // 어댑터를 설정하고 리사이클러뷰에 연결
        itemList = mutableListOf<ItemCommentModel>()
        adapter = MyCommentAdapter(this, itemList)
        binding.feedRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.feedRecyclerView.adapter = adapter

        // 메세지 전송 버튼 클릭 시
        binding.editBtn.setOnClickListener {
            val message = binding.editTxt.text.toString().trim() // 전송을 할 때 텍스트를 받아오기-선언 위치 중요
            if(message.isEmpty()){
                Toast.makeText(this, "댓글을 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                saveStore() //전송
                binding.editTxt.setText("") // 텍스트창 초기화
                // 어댑터 재실행
                getStore()
            }
        }

        // 메세지 입력 후 엔터 클릭 시에도 전송
        binding.editTxt.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                saveStore()
                binding.editTxt.setText("") // 텍스트창 초기화
                // 어댑터 재실행
                getStore()
                return@setOnEditorActionListener true
            }
            false
        }

        // 키보드가 활성화되면 리사이클러뷰의 크기 조정
        binding.feedRecyclerView.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            binding.feedRecyclerView.scrollToPosition(adapter.itemCount - 1)
        }

        MyApplication.db.collection("comments")
            .orderBy("time", Query.Direction.ASCENDING)
            //.whereEqualTo("sub_code", subCode)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.w(ContentValues.TAG, "Listen failed.", error)
                    return@addSnapshotListener
                }

                itemList = mutableListOf<ItemCommentModel>()
                for (document in snapshot!!) {
                    val item = document.toObject(ItemCommentModel::class.java)
                    if(reviewId.equals(item.reviewId)){
                        item.docId = document.id
                        itemList.add(item)
                    }
                }

                adapter.setData(itemList) // 어댑터 데이터 갱신
                // 가장 아래의 아이템으로 스크롤
                binding.feedRecyclerView.scrollToPosition(adapter.itemCount - 1)
            }
    }
    fun setProfileImage() {
        CoroutineScope(Dispatchers.Main).launch {
            var userProfile = MyApplication.getImageUrl(intent.getStringExtra("userEmail"))
            Toast.makeText(baseContext, "get profile${userProfile}", Toast.LENGTH_SHORT).show()
            if (!userProfile.isNullOrEmpty()) {
                // Glide를 사용하여 프로필 이미지 로드
                Glide.with(baseContext)
                    .load(userProfile)
                    .into(binding.profileImage)

            }

        }
    }

    override fun onResume() {
        super.onResume()

        // 데이터를 다시 불러오는 로직을 이곳에 추가
//        getUpdate()
    }



    fun updateCount(docRef: DocumentReference, updatedValue: Long) {
        val updates = hashMapOf<String, Any>(
            "userReviewCount" to updatedValue
        )

        docRef.update(updates)
            .addOnSuccessListener {
                // 업데이트 성공 처리
            }
            .addOnFailureListener { e ->
                // 업데이트 실패 처리
            }

    }

    fun dateToString(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREAN)
        return format.format(date)
    }

    fun saveStore() {
        val data = mapOf(
            "text" to binding.editTxt.text.toString(),
            "time" to dateToString(Date()),
            "user" to myName,
            "reviewId" to reviewId
        )

        MyApplication.db.collection("comments")
            .add(data)
            .addOnSuccessListener {
                Log.d("Chatting", "data firestore save ok")
            }
            .addOnFailureListener {
                Log.d("Chatting", "data firestore save error")
            }
    }

    fun getStore() {
        MyApplication.db.collection("comments")
            .orderBy("time", Query.Direction.ASCENDING)
            .whereEqualTo("reviewId", reviewId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.w(ContentValues.TAG, "Listen failed.", error)
                    return@addSnapshotListener
                }

                val itemList = mutableListOf<ItemCommentModel>()
                for (document in snapshot!!) {
                    val item = document.toObject(ItemCommentModel::class.java)
                    item.docId = document.id
                    itemList.add(item)
                }

                adapter.setData(itemList) // 어댑터 데이터 갱신
            }
    }

//    fun getUpdate() {
//        MyApplication.db.collection("reviews")
//            .addSnapshotListener{ snapshot, error ->
//                if (error != null) {
//                    Log.w(ContentValues.TAG, "Listen failed.", error)
//                    return@addSnapshotListener
//            }
//                val itemList = mutableListOf<ItemFeedModel>()
//                for (document in snapshot!!) {
//                    val item = document.toObject(ItemFeedModel::class.java)
//                    item.docId = document.id
//                    itemList.add(item)
//                }
//
//                adapter.notifyDataSetChanged(itemList) // 어댑터 데이터 갱신
//            }
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { //뒤로 가기 버튼
                onBackPressed() // 기본 뒤로가기 동작 수행
                return true
            }
//            R.id.menu_delete -> {
//
//            }
//            R.id.menu_update -> {
//
//            }
        }
        return super.onOptionsItemSelected(item)
    }

}
