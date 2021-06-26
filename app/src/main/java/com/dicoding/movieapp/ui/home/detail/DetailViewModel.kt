package com.dicoding.movieapp.ui.home.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.data.source.local.entity.MovieEntity
import com.dicoding.movieapp.data.source.local.entity.TvShowEntity
import com.dicoding.movieapp.repository.MovieCatalogueRepository
import com.dicoding.movieapp.utils.DataType
import com.dicoding.movieapp.utils.Resource

class DetailViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    private lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>
    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>

    fun setCatalog(id: Int, category: String) {
        when (category) {
            DataType.MOVIE.name -> {
                detailMovie = movieCatalogueRepository.getDetailMovie(id)
            }
            DataType.TV_SHOW.name -> {
                detailTvShow = movieCatalogueRepository.getDetailTvShow(id)
            }
        }
    }

    fun setFavoriteMovie() {
        val resource = detailMovie.value
        if (resource?.data != null) {
            val newState = !resource.data.isFavorite
            movieCatalogueRepository.setFavoriteMovie(resource.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detailTvShow.value
        if (resource?.data != null) {
            val newState = !resource.data.isFavorite
            movieCatalogueRepository.setFavoriteTvShow(resource.data, newState)
        }
    }

    fun getDetailTvShow() = detailTvShow
    fun getDetailMovie() = detailMovie
}