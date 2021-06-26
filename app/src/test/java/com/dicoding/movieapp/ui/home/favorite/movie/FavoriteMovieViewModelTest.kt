package com.dicoding.movieapp.ui.home.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.movieapp.data.source.local.entity.MovieEntity
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
class FavoriteMovieViewModelTest {

    private lateinit var viewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(movieCatalogueRepository)
    }

    @Test
    fun testGetFavoriteMovies() {
        val dummyMovie = pagedList
        Mockito.`when`(dummyMovie.size).thenReturn(3)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        Mockito.`when`(movieCatalogueRepository.getFavoriteMovies()).thenReturn(movies)
        val movie = viewModel.getFavoriteMovies().value
        verify(movieCatalogueRepository).getFavoriteMovies()
        Assert.assertNotNull(movie)
        Assert.assertEquals(3, movie?.size)

        viewModel.getFavoriteMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun testSetFavoriteMovie() {
        viewModel.setFavoriteMovie(DataDummy.getDummyDetailMovie())
        verify(movieCatalogueRepository).setFavoriteMovie(DataDummy.getDummyDetailMovie(), true)
        verifyNoMoreInteractions(movieCatalogueRepository)
    }
}