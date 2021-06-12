package com.dicoding.movieapp.data.source.remote.response.api

import com.dicoding.movieapp.data.source.remote.response.MovieDetailResponse
import com.dicoding.movieapp.data.source.remote.response.MovieResponse
import com.dicoding.movieapp.data.source.remote.response.TvShowDetailResponse
import com.dicoding.movieapp.data.source.remote.response.TvShowResponse
import com.dicoding.movieapp.utils.NetworkInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String = NetworkInfo.API_KEY
    ) : Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = NetworkInfo.API_KEY
    ) : Call<MovieDetailResponse>

    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey: String = NetworkInfo.API_KEY
    ) : Call<TvShowResponse>

    @GET("tv/{id}")
    fun getTvShowDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = NetworkInfo.API_KEY
    ) : Call<TvShowDetailResponse>
}