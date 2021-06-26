package com.dicoding.movieapp.ui.home.favorite.movie

import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.data.source.local.entity.MovieEntity
import com.dicoding.movieapp.repository.MovieCatalogueRepository

class FavoriteMovieViewModel (private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel(){
    fun getFavoriteMovies() = movieCatalogueRepository.getFavoriteMovies()

    fun setFavoriteMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.isFavorite
        movieCatalogueRepository.setFavoriteMovie(movieEntity, newState)
    }
}