package com.example.reviewmate.common

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository {

    companion object { // 정적 변수 설정
        fun getPopularMovies(page: Int = 1,
        onSuccess : (movies:List<Movie>) -> Unit,
        onError: () -> Unit) {
            val api = TMDBClient.api
            api.getPopularMovies(page = page)
                .enqueue(object : Callback<GetMoviesResponse>{
                    override fun onResponse(
                        call: Call<GetMoviesResponse>,
                        response: Response<GetMoviesResponse>
                    ) {
                        if(response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                onSuccess.invoke(responseBody.movies)
                            } else {
                                onError.invoke()
                            }
                        }else{
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                        onError.invoke()
                    }
                }

                )
        }
    }

}