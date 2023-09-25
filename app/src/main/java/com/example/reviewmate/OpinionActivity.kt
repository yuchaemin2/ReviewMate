package com.example.reviewmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import com.example.reviewmate.databinding.ActivityOpinionBinding
import com.example.reviewmate.databinding.ActivityReportBinding

class OpinionActivity : AppCompatActivity() {
    lateinit var binding: ActivityOpinionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpinionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var toolbar = binding.toolbarBack
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar?.setDisplayShowTitleEnabled(false)//타이틀 없애기

        val webView = findViewById<WebView>(R.id.webView)
        val url = "https://docs.google.com/forms/d/e/1FAIpQLSdD3fRONre37tQ0rKhoe0PccNryWDFL9cmSoSw2ganpXQho3Q/viewform?usp=sf_link"

        webView.settings.javaScriptEnabled = true // JavaScript를 활성화합니다.
        webView.loadUrl(url) // 원하는 웹 페이지의 URL을 로드합니다.
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