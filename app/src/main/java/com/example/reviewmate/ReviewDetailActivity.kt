package com.example.reviewmate

import android.app.AlertDialog
import android.app.Dialog
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
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.reviewmate.MyApplication.Companion.auth
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

        var toolbar = binding.toolbarBack
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayShowTitleEnabled(false)//타이틀 없애기

        // ------------- API : 영화 제목, 포스터 추가해야 함 --------------
        binding.movieTitle.text = intent.getStringExtra("movie")
        binding.movieRate.text = intent.getStringExtra("rate")
        binding.reviewTitle.text = intent.getStringExtra("title")
        binding.content.text = intent.getStringExtra("content")
        binding.userEmail.text = intent.getStringExtra("userEmail")
        binding.reviewDate.text = intent.getStringExtra("date")
        // 영화 API사용하여 데이터 가져와야 함

        var movieImage = intent.getStringExtra("movieImage")

        if(movieImage != null && movieImage != "null"){
            // Glide를 사용하여 프로필 이미지 로드
            Glide.with(baseContext)
                .load(movieImage)
                .into(binding.addImageView)
        }

        if(MyApplication.checkAuth()){
            if(binding.userEmail.text == MyApplication.email){
                binding.menuDelete.visibility = View.VISIBLE
                binding.menuUpdate.visibility = View.VISIBLE

                binding.menuUpdate.setOnClickListener (object : View.OnClickListener {
                    override fun onClick(view: View) {
                        // 클릭 시 배경색 변경
                        view.setBackgroundColor(Color.LTGRAY)

                        // 클릭 이후 원래 상태로 돌리기 위한 작업 (예: 지연 후 배경색 원복)
                        view.postDelayed({
                            view.setBackgroundColor(Color.TRANSPARENT)
                        }, 200)  // 200ms 동안 배경색 변경 후 원복
                    }
                })
                binding.menuDelete.setOnClickListener {

                }
            }
            else{
                binding.menuDelete.visibility = View.INVISIBLE
                binding.menuUpdate.visibility = View.INVISIBLE
            }
        }
    }

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





















