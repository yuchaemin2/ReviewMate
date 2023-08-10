package com.example.reviewmate

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.util.Log
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.reviewmate.MainActivity.Companion.MOVIE_POSTER
import com.example.reviewmate.MainActivity.Companion.MOVIE_TITLE
import com.example.reviewmate.MyApplication.Companion.auth
import com.example.reviewmate.databinding.ActivityAddBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.ktx.Firebase
import org.checkerframework.checker.units.qual.mm
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    lateinit var filePath: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val requestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode === android.app.Activity.RESULT_OK) {
//                Glide
//                    .with(applicationContext)
//                    .load(it.data?.data)
//                    .apply(RequestOptions().override(150,230))
//                    .centerCrop()
//                    .into(binding.addImageView)
                val cursor = contentResolver.query(it.data?.data as Uri, arrayOf<String>(MediaStore.Images.Media.DATA), null, null, null)
                cursor?.moveToFirst().let{
                    filePath = cursor?.getString(0) as String
                    Log.d("ToyProject", "${filePath}")
                }
            }
        }

//        binding.addImageView.setOnClickListener {
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*") // 갤러리에 있는 이미지 불러오는 방법
//            //intent.type = "image/*"
//            requestLauncher.launch(intent)
//        }

        binding.btnSave.setOnClickListener {
            if(binding.addTitleEditView.text.isNotEmpty()){
                saveStore()
            } else {
                Toast.makeText(this, "제목을 입력해주세요..", Toast.LENGTH_SHORT).show()
            }
            finish()
        }

        val movieTitle = intent.getStringExtra(MOVIE_TITLE)
        val moviePoster = intent.getStringExtra(MOVIE_POSTER)

        // 영화 제목과 포스터 정보를 UI에 설정
        binding.movieTitle.text = movieTitle
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342$moviePoster")
            .apply(RequestOptions().override(150, 230).centerCrop())
            .into(binding.addImageView)

    }

    fun dateToString(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREAN)
        return format.format(date)
    }

    fun saveStore() {
        val data = mapOf(
            "email" to Firebase.auth.currentUser!!.email,
            "title" to binding.addTitleEditView.text.toString(),
            "content" to binding.addEditView.text.toString(),
            "date" to dateToString(Date()),
            "movie" to binding.movieTitle.text.toString(),
            "rate" to binding.movieRate.text.toString(),
            "uid" to auth.uid
        )

        MyApplication.db.collection("reviews")
            .add(data)
            .addOnSuccessListener {
                Log.d("ToyProject", "data firestore save ok")
                uploadImage(it.id)
            }
            .addOnFailureListener {
                Log.d("ToyProject", "data firestore save error")
            }

        val userDocRef = MyApplication.db.collection("users").document(auth.uid.toString())
        MyApplication.db.collection("users").document("${MyApplication.auth.uid}")
            .get()
            .addOnSuccessListener {  documentSnapshot ->
                if(documentSnapshot.exists()) {
                    val currentCount = documentSnapshot.getLong("userReviewCount")
                    currentCount?.let {
                        val updatedCount = it + 1
                        updateCount(userDocRef, updatedCount)
                    }
                }
            }
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

    fun uploadImage(docId:String){
        val storage = MyApplication.storage
        val storageRef = storage.reference
        val imageRef = storageRef.child("images/${docId}.jpg")
        val file = Uri.fromFile(File(filePath))
        imageRef.putFile(file)
            .addOnSuccessListener {
                Log.d("ToyProject", "imageRef.putFile(file) - addOnSuccessListener")
                finish()
            }
            .addOnFailureListener {
                Log.d("ToyProject", "imageRef.putFile(file) - addOnFailureListener")
            }

    }
}
