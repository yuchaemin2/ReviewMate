package com.example.reviewmate

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.reviewmate.databinding.ActivityAddBinding
import com.example.reviewmate.databinding.ActivityUpdateBinding
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class UpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding

    lateinit var reviewId: String
    lateinit var filePath: String
    lateinit var movieImage: String
    lateinit var ratingbar: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reviewId = intent.getStringExtra("reviewId") ?: ""

        setListenerRatingBar()
        // 리뷰 데이터 가져와서 화면에 표시
        fetchReviewData()


        var toolbar = binding.toolbarBack
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayShowTitleEnabled(false)//타이틀 없애기
    }

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
            R.id.save_review -> {
                if (binding.addTitleEditView.text.isNotEmpty()) {
                    updateReview()
                    // UpdateActivity.kt

                    // ===============
                    // UpdateActivity.kt
                    // UpdateActivity.kt
                    val resultIntent = Intent()
                    resultIntent.putExtra("isUpdated", true)
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()



                    finish()
                } else {
                    Toast.makeText(this, "제목을 입력해주세요..", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun setListenerRatingBar() {
        ratingbar = binding.movieRate
        ratingbar.setOnRatingBarChangeListener { ratingbar, rating, fromUser ->
            ratingbar.rating=rating
            Toast.makeText(baseContext, "${rating}", Toast.LENGTH_SHORT).show()

        }
    }

    private fun fetchReviewData() {
        // reviewId를 사용하여 해당 리뷰 데이터 가져오기
        val db = Firebase.firestore
        val reviewRef = db.collection("reviews").document(reviewId)

        reviewRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val title = document.getString("title")
                    val content = document.getString("content")
                    val rate = document.getString("rate")
                    val movieImage = document.getString("movieImage")

                    val movieTitle = document.getString("movie")

                    binding.addTitleEditView.setText(title)
                    binding.addEditView.setText(content)
                    binding.movieRate.rating=rate!!.toFloat()
                    binding.movieTitle.text = movieTitle
                    if(movieImage != null && movieImage != "null"){
                        // Glide를 사용하여 프로필 이미지 로드
                        Glide.with(baseContext)
                            .load(movieImage)
                            .into(binding.addImageView)
                    }
                }
            }
            .addOnFailureListener { exception ->
                // 데이터 가져오기 실패 처리
            }
    }

    private fun updateReview() {
        val db = Firebase.firestore
        val title = binding.addTitleEditView.text.toString()
        val content = binding.addEditView.text.toString()
        val rate = binding.movieRate.rating.toString()

        if (title.isNotEmpty()) {
            // 리뷰 데이터 업데이트
            val reviewRef = db.collection("reviews").document(reviewId)
            val data = hashMapOf(
                "title" to title,
                "content" to content,
                "rate" to rate
                // 다른 필드도 필요한 경우 추가
            )

            reviewRef.update(data as Map<String, Any>)
                .addOnSuccessListener {
                    // 업데이트 성공 처리
                    setResult(RESULT_OK)
                    finish()
                }
                .addOnFailureListener { exception ->
                    // 업데이트 실패 처리
                }
        } else {
            // 제목이나 내용이 비어있을 경우 처리
            // 필요한 경우 사용자에게 알림을 보여줄 수 있습니다.
        }
    }
}