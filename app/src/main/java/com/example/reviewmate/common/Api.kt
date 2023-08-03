package com.example.reviewmate.common

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "8f3a3db8280b0610b5e33cee7ce41c59",
        @Query("page") page : Int,
        @Query("language") language : String = "ko"
    ): Call<GetMoviesResponse>
}