package com.example.reviewmate

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.reviewmate.databinding.ActivityAddBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode === android.app.Activity.RESULT_OK) {
                Glide
                    .with(applicationContext)
                    .load(it.data?.data)
                    .apply(RequestOptions().override(150,230))
                    .centerCrop()
                    .into(binding.addImageView)
                val cursor = contentResolver.query(it.data?.data as Uri, arrayOf<String>(MediaStore.Images.Media.DATA), null, null, null)
                cursor?.moveToFirst().let{
                    filePath = cursor?.getString(0) as String
                    Log.d("ToyProject", "${filePath}")
                }
            }
        }

        binding.addImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*") // 갤러리에 있는 이미지 불러오는 방법
            //intent.type = "image/*"
            requestLauncher.launch(intent)
        }

        binding.btnSave.setOnClickListener {
            if(binding.addTitleEditView.text.isNotEmpty()){
                saveStore()
            } else {
                Toast.makeText(this, "제목을 입력해주세요..", Toast.LENGTH_SHORT).show()
            }
            finish()
        }

    }

    fun dateToString(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREAN)
        return format.format(date)
    }

    fun saveStore() {
        val data = mapOf(
            "email" to MyApplication.email,
            "title" to binding.addTitleEditView.text.toString(),
            "content" to binding.addEditView.text.toString(),
            "date" to dateToString(Date()),
            "movie" to binding.movieTitle.text.toString(),
            "rate" to binding.movieRate.text.toString()
        )

        MyApplication.db.collection("reviews")
            .add(data)
            .addOnSuccessListener {
                Log.d("ToyProject", "data firestore save ok")
                uploadImage(it.id)
            }
            .addOnFailureListener {
                Log.d("ToyProject", "data firestore save error")
            }
    }
    fun uploadImage(docId:String){
        val storage = MyApplication.storage
        val storageRef = storage.reference
        val imageRef = storageRef.child("images/${docId}.jpg")
        val file = Uri.fromFile(File(filePath))
        imageRef.putFile(file)
            .addOnSuccessListener {
                Log.d("ToyProject", "imageRef.putFile(file) - addOnSuccessListener")
                finish()
            }
            .addOnFailureListener {
                Log.d("ToyProject", "imageRef.putFile(file) - addOnFailureListener")
            }

    }
}
