package com.example.reviewmate

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.reviewmate.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.yNotion.setOnClickListener {
            val url = "https://busy-pink-4c0.notion.site/My-log-639db7eaae7a40778db14e1740da78a2?pvs=4" // 여기에 열고자 하는 외부 링크를 입력하세요
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        binding.yGit.setOnClickListener {
            val url = "https://github.com/yuchaemin2/ReviewMate" // 여기에 열고자 하는 외부 링크를 입력하세요
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.kNotion.setOnClickListener {
            val url = "https://www.notion.so/20210699/HOME-9013d45c18a24e658fc81d19f3f6d6a1?pvs=4" // 여기에 열고자 하는 외부 링크를 입력하세요
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        binding.kGit.setOnClickListener {
            val url = "https://github.com/yuchaemin2/ReviewMate" // 여기에 열고자 하는 외부 링크를 입력하세요
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.cNotion.setOnClickListener {
            val url = "https://www.notion.so/097d66282086476e8c8fe02dcee8c13f?v=ea5b3f7979684766a1c08f281035bc8a&pvs=4" // 여기에 열고자 하는 외부 링크를 입력하세요
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        binding.cGit.setOnClickListener {
            val url = "https://github.com/yuchaemin2/ReviewMate" // 여기에 열고자 하는 외부 링크를 입력하세요
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        var toolbar = binding.toolbarBack
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayShowTitleEnabled(false)//타이틀 없애기
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
}