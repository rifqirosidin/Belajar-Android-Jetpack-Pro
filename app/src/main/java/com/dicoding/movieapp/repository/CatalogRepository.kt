package com.dicoding.movieapp.repository

import androidx.lifecycle.LiveData
import com.dicoding.movieapp.model.DataModel

interface CatalogRepository {

    fun getMovies(): LiveData<List<DataModel>>

    fun getMovieDetail(movieId: Int): LiveData<DataModel>

    fun getTvShows(): LiveData<List<DataModel>>

    fun getTvShowDetail(tvShowId: Int): LiveData<DataModel>
}