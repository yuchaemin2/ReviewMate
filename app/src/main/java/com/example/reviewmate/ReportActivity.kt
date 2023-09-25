package com.example.reviewmate

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import com.example.reviewmate.databinding.ActivityIntroBinding
import com.example.reviewmate.databinding.ActivityReportBinding

class ReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sendEmail()
    }

    private fun sendEmail() {
        val recipientEmail = "reviewmate2023@gmail.com" // 수신자 이메일 주소를 여기에 입력합니다.
        val subject = "[Android][리뷰메이트]Report"
        val message = """
            
            
            
            
                        ------------------------------
                        - App Version: 1.0.0
                        - User Account: ${MyApplication.email}
                    """.trimIndent()


        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$recipientEmail")
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            // 이메일 클라이언트 앱이 설치되어 있지 않은 경우 에러 메시지를 표시할 수 있습니다.
            // 이 부분을 원하는 방식으로 처리하세요.
        }
    }
}