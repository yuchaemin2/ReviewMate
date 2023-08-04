//package com.example.reviewmate
//
//import android.widget.ImageView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.google.firebase.storage.FirebaseStorage
//
//
//object BindingAdapter {
//    @JvmStatic
//    @Binding
//    fun loadImage(imageView: ImageView, url: String){
//
//        val storage: FirebaseStorage = FirebaseStorage.getInstance("gs://~")
//        val storageReference = storage.reference
//        val pathReference = storageReference.child("images/$url")
//
//        pathReference.downloadUrl.addOnSuccessListener { uri ->
//            Glide.with(imageView.context)
//                .load(uri)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .centerCrop()
//                .into(imageView)
//        }
//    }
//}
// https://velog.io/@hygge/Android-Kotlin-Firebase-Storage-%EC%97%B0%EA%B2%B0%ED%95%B4%EC%84%9C-DataBinding%EB%90%9C-RecyclerView%EC%97%90-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0