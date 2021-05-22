package com.dicoding.movieapp.ui.home.fragment.movie

import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.model.DataModel
import com.dicoding.movieapp.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovies(): List<DataModel> = DataDummy.createDataMovieDummy()
}