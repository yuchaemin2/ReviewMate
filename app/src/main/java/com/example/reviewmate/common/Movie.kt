package com.example.reviewmate.common

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val movieId : String? = null,
    @SerializedName("title") val movieTitle : String? = null,
    @SerializedName("overview") val movieOverview : String? = null,
    @SerializedName("poster_path") val moviePoster: String? = null,
    @SerializedName("backdrop_path") val movieBackdrop: String? = null,
    @SerializedName("vote_average") val movieRate: Double? = null,
    @SerializedName("release_date") val movieDate: String? = null,
    @SerializedName("popularity") val movieView: Float? = null,
) {}