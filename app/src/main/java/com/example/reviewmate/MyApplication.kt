package com.example.reviewmate

import android.content.ContentValues
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.MultiDexApplication
import com.bumptech.glide.Glide
import com.example.reviewmate.MyApplication.Companion.auth
import com.example.reviewmate.MyApplication.Companion.db
import com.example.reviewmate.MyApplication.Companion.storage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

class MyApplication : MultiDexApplication() {
    companion object{
        lateinit var db : FirebaseFirestore
        lateinit var storage : FirebaseStorage
        lateinit var auth : FirebaseAuth
        var email:String? = null
        var level:String? = null

        fun checkAuth(): Boolean{
            var currentuser = auth.currentUser
            return currentuser?.let{
                email = currentuser.email
                if(currentuser.isEmailVerified) true
                else false
            } ?: false


        }
        suspend fun getImageUrl(userEmail: String?): String? {
            return try {
                val querySnapshot = MyApplication.db.collection("users")
                    .whereEqualTo("userEmail", userEmail)
                    .get().await()

                if (!querySnapshot.isEmpty) {
                    val userDocument = querySnapshot.documents[0]
                    userDocument.getString("imageUrl")
                } else {
                    null
                }
            } catch (exception: Exception) {
                Log.e("MyFeedAdapter", "Error getting user document: $exception")
                null
            }
        }

    }


    override fun onCreate() {
        super.onCreate()
        auth = Firebase.auth

        db = FirebaseFirestore.getInstance()
        storage = Firebase.storage
    }

}