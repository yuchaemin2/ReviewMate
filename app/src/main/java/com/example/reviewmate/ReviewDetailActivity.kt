package com.example.reviewmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.reviewmate.databinding.ActivityReviewDetailBinding

class ReviewDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityReviewDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding을 사용하여 레이아웃 파일 설정
        binding =ActivityReviewDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


/*
*
val bundle : Bundle = Bundle()
                bundle.putString("email", data.email)
                bundle.putString("title", data.title)
                bundle.putString("content", data.content)
                bundle.putString("date", data.date)
                bundle.putString("movie", data.movie)
                bundle.putString("rate", data.rate)
* */

        // ------------- API : 영화 제목, 포스터 추가해야 함 --------------
        binding.movieTitle.text = intent.getStringExtra("movie")
        binding.movieRate.text = intent.getStringExtra("rate")
        binding.reviewTitle.text = intent.getStringExtra("title")
        binding.content.text = intent.getStringExtra("content")
        binding.userEmail.text = intent.getStringExtra("userEmail")
        binding.reviewDate.text = intent.getStringExtra("date")

        var profileImageUrl = intent.getStringExtra("image_url")

        // 영화 API사용하여 데이터 가져와야 함
        if(profileImageUrl != null && profileImageUrl != "null"){
            // Glide를 사용하여 프로필 이미지 로드
            Glide.with(baseContext)
                .load(profileImageUrl)
                .into(binding.profileImage)
        }
        var movieImage = intent.getStringExtra("movieImage")
        Toast.makeText(baseContext, "${movieImage}영화 이미지 왜안돼?..????", Toast.LENGTH_SHORT).show()
        if(movieImage != null && movieImage != "null"){
            // Glide를 사용하여 프로필 이미지 로드
            Glide.with(baseContext)
                .load(movieImage)
                .into(binding.addImageView)
            Toast.makeText(baseContext, "${movieImage}영화 이미지 왜안돼?..????", Toast.LENGTH_SHORT).show()
        }
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment)
//    }

}





















