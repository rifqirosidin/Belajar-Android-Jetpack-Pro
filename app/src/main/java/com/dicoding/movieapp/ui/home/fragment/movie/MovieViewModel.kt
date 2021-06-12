package com.dicoding.movieapp.ui.home.fragment.movie

import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.repository.MovieCatalogueRepository

class MovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getMovies() = movieCatalogueRepository.getMovies();
}