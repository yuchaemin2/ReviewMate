package com.example.reviewmate

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.reviewmate.databinding.ActivityReviewDetailBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.File

class ReviewDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityReviewDetailBinding

    lateinit var file: File
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding을 사용하여 레이아웃 파일 설정
        binding =ActivityReviewDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // ------------- API : 영화 제목, 포스터 추가해야 함 --------------
        binding.movieTitle.text = intent.getStringExtra("영화제목API")
        binding.movieRate.text = intent.getStringExtra("rate")
        binding.reviewTitle.text = intent.getStringExtra("title")
        binding.content.text = intent.getStringExtra("content")
        binding.userEmail.text = intent.getStringExtra("userEmail")
        binding.reviewDate.text = intent.getStringExtra("date")
        // 영화 API사용하여 데이터 가져와야 함


    }

}





















