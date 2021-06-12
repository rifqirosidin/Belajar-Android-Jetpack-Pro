package com.dicoding.movieapp.ui.home.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.model.DataModel
import com.dicoding.movieapp.repository.MovieCatalogueRepository

class DetailViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getMovieDetail(movieId: Int): LiveData<DataModel> =
        movieCatalogueRepository.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<DataModel> = movieCatalogueRepository.getTvShowDetail(
        tvShowId
    )
}