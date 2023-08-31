package com.example.reviewmate

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.reviewmate.MainActivity.Companion.MOVIE_ID
import com.example.reviewmate.MyApplication.Companion.auth
import com.example.reviewmate.databinding.ActivityAddBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.collection.LLRBNode
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
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
    lateinit var movieImage: String
    lateinit var userEmail: String
    lateinit  var ratingbar : RatingBar
    lateinit var userRateStr : String

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

        val movieTitle = intent.getStringExtra(MainActivity.MOVIE_TITLE)
        val moviePoster = intent.getStringExtra(MainActivity.MOVIE_POSTER)
        if(moviePoster !== null) { movieImage = moviePoster.toString() }
        val movieId = intent.getStringExtra(MainActivity.MOVIE_ID)


        binding.movieTitle.text = movieTitle
        binding.movieId.text = movieId
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342${moviePoster}")
            .apply(RequestOptions().override(150, 230).centerCrop())
            .into(binding.addImageView)

        ratingbar = binding.movieRate
        ratingbar.setOnRatingBarChangeListener { ratingbar, rating, fromUser ->
            ratingbar.rating=rating
            Toast.makeText(baseContext, "${rating}", Toast.LENGTH_SHORT).show()
            //binding.textViewMovieRate.text= rating.toString()
            userRateStr = rating.toString()

        }


        var toolbar = binding.toolbarBack
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayShowTitleEnabled(false)//타이틀 없애기
    }
   // ===================================================


// ===============================================================================
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_nav, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { //뒤로 가기 버튼
                onBackPressed() // 기본 뒤로가기 동작 수행
                return true
            }
            R.id.save_review ->{
                if(binding.addTitleEditView.text.isNotEmpty()){
                    saveStore()
                    finish()
                } else {
                    Toast.makeText(this, "제목을 입력해주세요..", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun dateToString(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREAN)
        return format.format(date)
    }

    fun saveStore() {
        val data = mapOf(
            "email" to MyApplication.email,
            "title" to binding.addTitleEditView.text.toString(),
            "content" to binding.addEditView.text.toString(),
            "date" to dateToString(Date()),
            "movie" to binding.movieTitle.text.toString(),
            "rate" to userRateStr,
            "uid" to auth.uid,
            "movieId" to binding.movieId.text.toString(),
            "movieImage" to "https://image.tmdb.org/t/p/w342${movieImage}"
        )

        MyApplication.db.collection("reviews")
            .add(data)
            .addOnSuccessListener {
                Log.d("ToyProject", "data firestore save ok")
//                uploadImage(it.id)
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

    fun uploadImage(movieId:String){
        val storage = MyApplication.storage
        val storageRef = storage.reference
        val imageRef = storageRef.child("images/${movieId}.jpg")
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
