package com.dicoding.movieapp.ui.home.favorite.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.data.source.local.entity.TvShowEntity
import com.dicoding.movieapp.repository.MovieCatalogueRepository

class FavoriteTvShowViewModel (private val repository: MovieCatalogueRepository) : ViewModel() {

    fun getFavoriteTvShows() = repository.getFavoriteTvShows()

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.isFavorite
        repository.setFavoriteTvShow(tvShowEntity, newState)
    }
}