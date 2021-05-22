package com.dicoding.movieapp.ui.home.fragment.tv_show

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel;

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun testGetTvShows() {
        val viewModelTvShow = viewModel.getTvShows()
        Assert.assertNotNull(viewModelTvShow)
        Assert.assertEquals(10, viewModelTvShow.size)
    }
}