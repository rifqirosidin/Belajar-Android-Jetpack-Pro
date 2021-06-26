package com.dicoding.movieapp.ui.home.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.movieapp.data.source.local.entity.MovieEntity
import com.dicoding.movieapp.data.source.local.entity.TvShowEntity
import com.dicoding.movieapp.repository.MovieCatalogueRepository
import com.dicoding.movieapp.utils.DataDummy
import com.dicoding.movieapp.utils.DataType
import com.dicoding.movieapp.utils.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyDataMovie = DataDummy.getDummyDetailMovie()
    private val movieId = dummyDataMovie.id

    private val dummyDataTvShow = DataDummy.getDummyDetailTvShow()
    private val tvShowId = dummyDataTvShow.id

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieCatalogueRepository)
    }

    @Test
    fun testGetMovieById() {
        val dummyDetailMovie = Resource.success(DataDummy.getDummyDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        Mockito.`when`(movieId?.let { movieCatalogueRepository.getDetailMovie(it) }).thenReturn(movie)

        if (movieId != null) {
            viewModel.setCatalog(movieId, DataType.MOVIE.name)
        }
        viewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetailMovie = Resource.success(DataDummy.getDummyDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        Mockito.`when`(movieId?.let { movieCatalogueRepository.getDetailMovie(it) }).thenReturn(movie)

        if (movieId != null) {
            viewModel.setCatalog(movieId, DataType.MOVIE.name)
        }
        viewModel.setFavoriteMovie()
        verify(movieCatalogueRepository).setFavoriteMovie(movie.value!!.data as MovieEntity, true)
        verifyNoMoreInteractions(movieObserver)
    }

    @Before
    fun setupTvShow() {
        viewModel = DetailViewModel(movieCatalogueRepository)
    }

    @Test
    fun testGetTvShowById() {
        val dummyDetailTvShow = Resource.success(DataDummy.getDummyDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        Mockito.`when`(tvShowId?.let { movieCatalogueRepository.getDetailTvShow(it) }).thenReturn(tvShow)

        if (tvShowId != null) {
            viewModel.setCatalog(tvShowId, DataType.TV_SHOW.name)
        }
        viewModel.getDetailTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetailTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyDetailTvShow = Resource.success(DataDummy.getDummyDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        Mockito.`when`(tvShowId?.let { movieCatalogueRepository.getDetailTvShow(it) }).thenReturn(tvShow)

        if (tvShowId != null) {
            viewModel.setCatalog(tvShowId, DataType.TV_SHOW.name)
        }
        viewModel.setFavoriteTvShow()
        verify(movieCatalogueRepository).setFavoriteTvShow(tvShow.value!!.data as TvShowEntity, true)
        verifyNoMoreInteractions(tvShowObserver)
    }
}