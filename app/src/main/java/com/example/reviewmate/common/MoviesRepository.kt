package com.example.reviewmate.common

import com.example.reviewmate.MyApplication
import com.example.reviewmate.common.TMDBClient.api
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

        fun getTopRatedMovies(page: Int = 1,
                              onSuccess: (movies: List<Movie>) -> Unit,
                              onError: () -> Unit ) {
            api.getTopRatedMovies(page = page)
                .enqueue(object : Callback<GetMoviesResponse> {
                    override fun onResponse(
                        call: Call<GetMoviesResponse>,
                        response: Response<GetMoviesResponse>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                onSuccess.invoke(responseBody.movies)
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                        onError.invoke()
                    }
                })
        }

        fun getUpcomingMovies( page: Int = 1,
                               onSuccess: (movies: List<Movie>) -> Unit,
                               onError: () -> Unit ) {
            api.getUpcomingMovies(page = page)
                .enqueue(object : Callback<GetMoviesResponse> {
                    override fun onResponse(
                        call: Call<GetMoviesResponse>,
                        response: Response<GetMoviesResponse>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                onSuccess.invoke(responseBody.movies)
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                        onError.invoke()
                    }
                })
        }


        fun getLikedMovies(
            page: Int = 1,
            onSuccess: (movies: List<Movie>) -> Unit,
            onError: () -> Unit
        ) {
            MyApplication.db.collection("users").document(MyApplication.auth.uid.toString()).collection("liked_movies")
                .get()
                .addOnSuccessListener { documents ->
                    val movies = mutableListOf<Movie>()
                    for (document in documents) {
                        val movie = document.toObject(Movie::class.java)
                        movies.add(movie)
                    }
                    onSuccess.invoke(movies)
                }
                .addOnFailureListener {
                    onError.invoke()
                }
        }

        fun getSearchMovies( page: Int = 1, query: String,
                             onSuccess: (movies: List<Movie>) -> Unit,
                             onError: () -> Unit ) {
            api.getSearchMovies(page = page, query = query)
                .enqueue(object : Callback<GetMoviesResponse> {
                    override fun onResponse(
                        call: Call<GetMoviesResponse>,
                        response: Response<GetMoviesResponse>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                onSuccess.invoke(responseBody.movies)
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                        onError.invoke()
                    }
                })
        }
    }

}