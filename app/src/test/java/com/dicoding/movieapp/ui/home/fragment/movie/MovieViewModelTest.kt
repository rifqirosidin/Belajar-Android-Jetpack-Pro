package com.dicoding.movieapp.ui.home.fragment.movie

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp(){
        viewModel = MovieViewModel()
    }

    @Test
    fun testGetMovies() {
        val viewModelMovie = viewModel.getMovies();
        Assert.assertNotNull(viewModelMovie)
        Assert.assertEquals(10, viewModelMovie.size)
    }
}