package com.example.reviewmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.reviewmate.MainActivity.Companion.MOVIE_TITLE
import com.example.reviewmate.databinding.ActivityReviewDetailBinding

class ReviewDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityReviewDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding을 사용하여 레이아웃 파일 설정
        binding =ActivityReviewDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // ------------- API : 영화 제목, 포스터 추가해야 함 --------------
        val movieTitle = intent.getStringExtra(MOVIE_TITLE)
        val moviePoster = intent.getStringExtra(MainActivity.MOVIE_POSTER)

        // 영화 제목과 포스터 정보를 UI에 설정
        binding.movieTitle.text = movieTitle
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342${moviePoster}")
            .apply(RequestOptions().override(150, 230).centerCrop())
            .into(binding.addImageView)

        binding.movieRate.text = intent.getStringExtra("rate")
        binding.reviewTitle.text = intent.getStringExtra("title")
        binding.content.text = intent.getStringExtra("content")
        binding.userEmail.text = intent.getStringExtra("userEmail")
        binding.reviewDate.text = intent.getStringExtra("date")
        // 영화 API사용하여 데이터 가져와야 함

    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment)
//    }

}





















