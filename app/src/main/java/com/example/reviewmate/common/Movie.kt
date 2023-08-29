package com.example.reviewmate.common

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val movieId : String,
    @SerializedName("title") val movieTitle : String,
    @SerializedName("overview") val movieOverview : String,
    @SerializedName("poster_path") val moviePoster: String,
    @SerializedName("backdrop_path") val movieBackdrop: String,
    @SerializedName("vote_average") val movieRate: Float,
    @SerializedName("release_date") val movieDate: String,
    @SerializedName("popularity") val movieView: Float

) {
    constructor() : this("", "+", "", "", "", 0f, "", 0f) // 빈 생성자 추가
}