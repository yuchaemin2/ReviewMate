package com.example.reviewmate

import android.content.ContentValues
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
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

import kotlinx.coroutines.tasks.await

class MyApplication : MultiDexApplication() {
    companion object{
        lateinit var db : FirebaseFirestore
        lateinit var storage : FirebaseStorage
        lateinit var auth : FirebaseAuth
        var email:String? = null
        var userlevel:String? = null
        var imageurl:String? = null

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

//        val userDocRef = db.collection("users").document(auth.currentUser!!.uid)
//        userDocRef.get()
//            .addOnSuccessListener { documentSnapshot ->
//                if (documentSnapshot.exists()) {
//                    userlevel = documentSnapshot.getString("userLevel")
//                    imageurl = documentSnapshot.getString("imageUrl")
//                }else {
//                    Log.d("ToyProject", "No such document")
//                }
//            }
//            .addOnFailureListener { e ->
//                Log.d("UserInfo", "Error getting document: ${e.message}")
//            }

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userDocRef = db.collection("users").document(currentUser.uid)
            userDocRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        userlevel = documentSnapshot.getString("userLevel")
                        imageurl = documentSnapshot.getString("imageUrl")
                    } else {
                        Log.d("ToyProject", "No such document")
                    }
                }
                .addOnFailureListener { e ->
                    Log.d("UserInfo", "Error getting document: ${e.message}")
                }
        } else {
            Log.d("UserInfo", "Current user is null")
        }

        if(true) {
            var userInfo = UserModel()

            userInfo.uid = auth.uid
            userInfo.userEmail = auth.currentUser?.email

            db.collection("users").document(auth.uid.toString())
                .get()
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        val document = task.result
                        if (document.exists()){ Log.d("ToyProject", "이미 존재하는 계정입니다.") }
                        else {
                            userInfo.imageUrl = "https://firebasestorage.googleapis.com/v0/b/reviewmate-59794.appspot.com/o/profile_images%2Fimg_1.png?alt=media&token=eb7e37c7-bbc3-4ef5-9491-bbca0f8c60bc"
                            db.collection("users").document(auth.uid.toString()).set(userInfo)
                            Log.d("ToyProject", "계정을 user collection에 추가했습니다.")
                        }
                    }
                }
        }
    }

}