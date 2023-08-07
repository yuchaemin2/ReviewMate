package com.example.reviewmate.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TMDBClient {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create()) // 데이터 파싱 converter
        .build()

    val api = retrofit.create(Api::class.java) // 인터페이스 구현
}