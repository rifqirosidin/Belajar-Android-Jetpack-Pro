package com.dicoding.movieapp.ui.home.fragment.tv_show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.movieapp.data.source.local.entity.TvShowEntity
import com.dicoding.movieapp.repository.MovieCatalogueRepository
import com.dicoding.movieapp.utils.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieCatalogueRepository)
    }

    @Test
    fun getTvShows(){
        val dummyTvShow = Resource.success(pagedList)
        Mockito.`when`(dummyTvShow.data?.size).thenReturn(3)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(movieCatalogueRepository.getTvShows()).thenReturn(tvShows)
        val tvShow = viewModel.getTvShows().value?.data
        verify(movieCatalogueRepository).getTvShows()
        Assert.assertNotNull(tvShow)
        Assert.assertEquals(3, tvShow?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

}