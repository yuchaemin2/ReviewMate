package com.example.reviewmate.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object TMDBClient {
//    val retrofit = Retrofit.Builder()
//        .baseUrl("https://api.themoviedb.org/3/")
//        .addConverterFactory(GsonConverterFactory.create()) // 데이터 파싱 converter
//        .build()
//
//    val api = retrofit.create(Api::class.java) // 인터페이스 구현
//}

object TMDBClient {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create()) // 데이터 파싱 converter
        .build()

    val api = retrofit.create(Api::class.java) // 인터페이스 구현
}

/*
* val client = OkHttpClient()

val request = Request.Builder()
  .url("https://api.themoviedb.org/3/search/movie?include_adult=false&language=en-US&page=1")
  .get()
  .addHeader("accept", "application/json")
  .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4ZjNhM2RiODI4MGIwNjEwYjVlMzNjZWU3Y2U0MWM1OSIsInN1YiI6IjY0YmY0MzI2NmQ0Yzk3MDEzOTNjYjhlNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zs2VVryNqVsoaU-FiV5zQhn6Eq9B5oGveFW36SrLDiI")
  .build()

val response = client.newCall(request).execute()
*
* */