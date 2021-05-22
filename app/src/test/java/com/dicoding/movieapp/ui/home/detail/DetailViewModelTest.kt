package com.dicoding.movieapp.ui.home.detail

import com.dicoding.movieapp.utils.DataDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.createDataMovieDummy()[0]
    private val movieId = dummyMovie.id;

    private val dummyTvShow = DataDummy.createDataTvShowDummy()[0]
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp(){
        viewModel = DetailViewModel()
    }

    @Test
    fun testGetMovieById() {
        viewModel.setMovieId(movieId)
        val movie = viewModel.getMovieById()
        Assert.assertNotNull(movie)
        Assert.assertEquals(dummyMovie.id, movie.id)
        Assert.assertEquals(dummyMovie.name, movie.name)
        Assert.assertEquals(dummyMovie.poster, movie.poster)
        Assert.assertEquals(dummyMovie.img_preview, movie.img_preview)
    }

    @Test
    fun testGetTvShowById() {
        viewModel.setTvShowId(tvShowId)
        val tvShow = viewModel.getTvShowById()
        Assert.assertNotNull(tvShow)
        Assert.assertEquals(dummyTvShow.id, tvShow.id)
        Assert.assertEquals(dummyTvShow.name, tvShow.name)
        Assert.assertEquals(dummyTvShow.poster, tvShow.poster)
        Assert.assertEquals(dummyTvShow.img_preview, tvShow.img_preview)
    }
}