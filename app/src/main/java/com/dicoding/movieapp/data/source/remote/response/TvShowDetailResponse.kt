package com.dicoding.movieapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowDetailResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String
)