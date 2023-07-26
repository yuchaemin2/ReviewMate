package com.example.reviewmate

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.reviewmate.databinding.ActivityAddBinding
import com.example.reviewmate.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            if(binding.addEditViewTitle.text.isNotEmpty()) {
                // firestore 저장
                saveStore()
            } else {
                Toast.makeText(this, "내용을 입력해주세요..", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

    fun dateToString(date: Date): String{
        val format = SimpleDateFormat("yyyy-mm-dd hh:mm")
        return format.format(date)
    }

    fun saveStore(){
        val data = mapOf(
            "title" to binding.addEditViewTitle.text.toString(),
            "content" to binding.addEditViewContent.text.toString(),
            "data" to dateToString(Date())
        )
    }
}