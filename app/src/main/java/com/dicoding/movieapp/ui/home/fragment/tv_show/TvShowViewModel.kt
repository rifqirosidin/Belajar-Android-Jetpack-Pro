package com.dicoding.movieapp.ui.home.fragment.tv_show

import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.repository.MovieCatalogueRepository

class TvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository): ViewModel() {
    fun getTvShows() = movieCatalogueRepository.getTvShows()

}