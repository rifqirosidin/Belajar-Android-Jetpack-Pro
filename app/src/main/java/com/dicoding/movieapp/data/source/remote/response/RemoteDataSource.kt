package com.dicoding.movieapp.data.source.remote.response

import android.util.Log
import com.dicoding.movieapp.data.source.remote.response.api.ApiClient
import com.dicoding.movieapp.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this){
            instance ?: RemoteDataSource()
        }
    }

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        val client = ApiClient.getApiService().getMovies()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback.onMoviesLoaded(response.body()?.results)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getMovieDetail(callback: LoadMovieDetailCallback, movieId: Int) {
        EspressoIdlingResource.increment()
        val client = ApiClient.getApiService().getMovieDetail(movieId)
        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                callback.onMovieDetailLoaded(response.body())
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e("getMovieDetail", "onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

     fun getTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        val client = ApiClient.getApiService().getTvShows()
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                callback.onTvShowsLoaded(response.body()?.results)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getTvShows onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
    }

     fun getTvShowDetail(callback: LoadTvShowDetailCallback, tvShowId: Int) {
         EspressoIdlingResource.increment()
         val client = ApiClient.getApiService().getTvShowDetail(tvShowId)
         client.enqueue(object : Callback<TvShowDetailResponse> {
             override fun onResponse(call: Call<TvShowDetailResponse>, response: Response<TvShowDetailResponse>) {
                 callback.onTvShowDetailLoaded(response.body())
                 EspressoIdlingResource.decrement()
             }

             override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                 Log.e("RemoteDataSource", "getDetailTvShow onFailure : ${t.message}")
                 EspressoIdlingResource.decrement()
             }
         })
    }

    interface LoadMoviesCallback {
        fun onMoviesLoaded(movies: List<Movie>?)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailLoaded(movieDetail : MovieDetailResponse?)
    }

    interface LoadTvShowsCallback {
        fun onTvShowsLoaded(tvShows : List<TvShow>?)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailLoaded(tvShowDetail: TvShowDetailResponse?)
    }
}