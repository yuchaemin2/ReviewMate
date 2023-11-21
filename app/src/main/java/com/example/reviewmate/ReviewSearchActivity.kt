package com.example.reviewmate

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reviewmate.MyApplication.Companion.db
import com.example.reviewmate.databinding.ActivityReviewSearchBinding
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReviewSearchActivity : AppCompatActivity() {
    lateinit var binding: ActivityReviewSearchBinding
    var firestore : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityReviewSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var toolbar = binding.toolbarBack
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayShowTitleEnabled(false)//타이틀 없애기

        // 파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()

        binding.recyclerview.adapter = RecyclerViewAdapter()
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        // 검색 옵션 변수
        var searchOption = "name"

        // 스피너 옵션에 따른 동작
        binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (binding.spinner.getItemAtPosition(position)) {
                    "제목" -> {
                        searchOption = "title"
                    }
                    "사용자" -> {
                        searchOption = "email"
                    }
                    "내용" -> {
                        searchOption = "content"
                    }
                    "영화제목" -> {
                        searchOption = "movie"
                    }
                }
            }
        }

        // 검색 옵션에 따라 검색
        binding.searchBtn.setOnClickListener {
            (binding.recyclerview.adapter as RecyclerViewAdapter).search(binding.searchWord.text.toString(), searchOption)
        }

        binding.btnAddlecture.setOnClickListener{
            intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_back, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { //뒤로 가기 버튼
                onBackPressed() // 기본 뒤로가기 동작 수행
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        // Person 클래스 ArrayList 생성성
        var itemList : ArrayList<ItemFeedModel> = arrayListOf()

        init {  // telephoneBook의 문서를 불러온 뒤 Person으로 변환해 ArrayList에 담음
            firestore?.collection("reviews")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                // ArrayList 비워줌
                itemList.clear()

                for (snapshot in querySnapshot!!.documents) {
                    var item = snapshot.toObject(ItemFeedModel::class.java)
                    itemList.add(item!!)
                }
                notifyDataSetChanged()
            }
        }

        // xml파일을 inflate하여 ViewHolder를 생성
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
            return ViewHolder(view)
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        }

        // onCreateViewHolder에서 만든 view와 실제 데이터를 연결
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder = (holder as ViewHolder).itemView
            val data = itemList!!.get(position)
            val delete = viewHolder.findViewById<ImageView>(R.id.review_delete)

            viewHolder.findViewById<TextView>(R.id.itemTitleView).text = itemList[position].title
            viewHolder.findViewById<TextView>(R.id.itemEmailView).text = itemList[position].email
            viewHolder.findViewById<TextView>(R.id.itemContentView).text =
                itemList[position].content
            viewHolder.findViewById<TextView>(R.id.itemMovieView).text = itemList[position].movie

            viewHolder.findViewById<TextView>(R.id.itemTitleView).setOnClickListener {
                val bundle: Bundle = Bundle()
                bundle.putString("title", data.title)
                bundle.putString("content", data.content)
                bundle.putString("date", data.date)
                bundle.putString("movie", data.movie)
                bundle.putString("rate", data.rate)
                bundle.putString("userEmail", data.email)
                bundle.putString("date", data.date)
                bundle.putString("movieImage", data.movieImage)

                Intent(this@ReviewSearchActivity, ReviewDetailActivity::class.java).apply {
                    putExtras(bundle)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run {
                    this@ReviewSearchActivity.startActivity(this)
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                val profileImageUrl = MyApplication.getImageUrl(data.email)
                if (!profileImageUrl.isNullOrEmpty()) {
                    // Glide를 사용하여 프로필 이미지 로드
                    Glide.with(this@ReviewSearchActivity)
                        .load(profileImageUrl)
                        .into(viewHolder.findViewById<ImageView>(R.id.itemImageView))

                } else {
                    Toast.makeText(this@ReviewSearchActivity, "no profile", Toast.LENGTH_SHORT)
                        .show()
                    //profileImageUrl=MyApplication.getImageUrl(data.email)
                    Toast.makeText(
                        this@ReviewSearchActivity,
                        "${profileImageUrl}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                val alertHandler = object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                if (data.docId !== null) {
                                    MyApplication.storage.getReference().child("images")
                                        .child("${data.docId!!}.jpg")
                                        .delete()
                                    db.collection("reviews").document("${data.docId}")
                                        .delete()
                                        .addOnSuccessListener {
                                            Toast.makeText(
                                                this@ReviewSearchActivity,
                                                "삭제가 완료되었습니다.",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                            val userDocRef =
                                                MyApplication.db.collection("users").document(
                                                    MyApplication.auth.uid.toString()
                                                )
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
                                                            removeItem(holder.adapterPosition)
                                                        }
                                                    }
                                                }
                                        }
                                        .addOnFailureListener {
                                            Toast.makeText(
                                                this@ReviewSearchActivity,
                                                "삭제가 실패하였습니다.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                } else {
                                    Toast.makeText(
                                        this@ReviewSearchActivity,
                                        "문서가 존재하지 않습니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                                Log.d("ToyProject", "DialogInterface.BUTTON_NEGATIVE")
                            }
                        }
                    }
                }

                if (data.email == MyApplication.email) {
                    delete.visibility = View.VISIBLE
                    delete.setOnClickListener {
                        AlertDialog.Builder(this@ReviewSearchActivity).run {
                            setTitle("정말 삭제하시겠습니까?")
                            setMessage("한 번 삭제하면 되돌릴 수 없습니다.")
                            setNegativeButton("Cancle", alertHandler)
                            setPositiveButton("Yes", alertHandler)
                            show()
                        }
                    }
                } else {
                    delete.visibility = View.GONE
                }

                val imageRef = MyApplication.storage.reference.child("images/${data.docId}.jpg")
                imageRef.downloadUrl.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
//                // 다운로드 이미지를 ImageView에 보여줌
                        GlideApp.with(this@ReviewSearchActivity)
                            .load(task.result)
                            .into(viewHolder.findViewById<ImageView>(R.id.itemImageView))
                    }
                }

            }
        }

        private fun removeItem(position: Int) {
            itemList.removeAt(position)
            notifyItemRemoved(position)
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

        // 리사이클러뷰의 아이템 총 개수 반환
        override fun getItemCount(): Int {
            return itemList.size
        }

        // 파이어스토어에서 데이터를 불러와서 검색어가 있는지 판단
        fun search(searchWord : String, option : String) {
            firestore?.collection("reviews")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                // ArrayList 비워줌
                itemList.clear()

                for (snapshot in querySnapshot!!.documents) {
                    if (snapshot.getString(option)!!.contains(searchWord)) {
                        var item = snapshot.toObject(ItemFeedModel::class.java)
                        itemList.add(item!!)
                    }
                    if(itemList.size.equals(0)){
                        binding.textView.visibility = View.VISIBLE
                        binding.btnAddlecture.visibility = View.VISIBLE
                    }
                    else{
                        binding.textView.visibility = View.GONE
                        binding.btnAddlecture.visibility = View.GONE
                    }
                }
                notifyDataSetChanged()
            }
        }
    }
}