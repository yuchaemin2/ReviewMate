package com.example.reviewmate

import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MyApplication: MultiDexApplication() {
    companion object {
        lateinit var auth: FirebaseAuth // 어플리케이션이 이곡저곳에서 쉽게 이용하도록 어플리케이션에서 사용하고자
        var email: String? = null // 이후에 설정할 값 유저의 이메일 값
        // 인증여부 확인 함수
        fun checkAuth(): Boolean {
            var currentUser = auth.currentUser

            return currentUser?.let {
                email = currentUser.email
                currentUser.isEmailVerified
            } ?: let {
                false
            }
        }
    }

    // 로그아웃 메서드 추가
    fun signOut() {
        auth.signOut()
        email = null
    }

    override fun onCreate() {
        super.onCreate()
        auth = Firebase.auth
    }
}