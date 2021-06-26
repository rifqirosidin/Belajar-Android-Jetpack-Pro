package com.dicoding.movieapp.ui.home.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.movieapp.data.source.local.entity.TvShowEntity
import com.dicoding.movieapp.repository.MovieCatalogueRepository
import com.dicoding.movieapp.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest {

    private lateinit var viewModel: FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowViewModel(movieCatalogueRepository)
    }

    @Test
    fun testGetFavoriteTvShows() {
        val dummyTvShow = pagedList
        Mockito.`when`(dummyTvShow.size).thenReturn(3)
        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(movieCatalogueRepository.getFavoriteTvShows()).thenReturn(tvShows)
        val tvShow = viewModel.getFavoriteTvShows().value
        verify(movieCatalogueRepository).getFavoriteTvShows()
        Assert.assertNotNull(tvShow)
        Assert.assertEquals(3, tvShow?.size)

        viewModel.getFavoriteTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

    @Test
    fun testSetFavoriteTvShow() {
        viewModel.setFavoriteTvShow(DataDummy.getDummyDetailTvShow())
        verify(movieCatalogueRepository).setFavoriteTvShow(DataDummy.getDummyDetailTvShow(), true)
        verifyNoMoreInteractions(movieCatalogueRepository)
    }
}