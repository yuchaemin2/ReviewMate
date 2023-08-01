package com.example.reviewmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
        binding.movieTitle.text = intent.getStringExtra("영화제목API")
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





















