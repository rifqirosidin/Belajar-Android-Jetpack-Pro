package com.dicoding.movieapp.ui.home.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.movieapp.model.DataModel
import com.dicoding.movieapp.repository.MovieCatalogueRepository
import com.dicoding.movieapp.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
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
    private val dummyDataMovie = DataDummy.createDataMovieDummy()[0]
    private val movieId = dummyDataMovie.id;

    private val dummyDataTvShow = DataDummy.createDataTvShowDummy()[0]
    private val tvShowId = dummyDataTvShow.id

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<DataModel>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieCatalogueRepository)
    }

    @Test
    fun testGetMovieById() {
    val movieDummy = MutableLiveData<DataModel>()
    movieDummy.value = dummyDataMovie

    Mockito.`when`(movieCatalogueRepository.getMovieDetail(movieId)).thenReturn(movieDummy)

    val movieData = viewModel.getMovieDetail(movieId).value as DataModel

    junit.framework.Assert.assertNotNull(movieData)
    assertEquals(dummyDataMovie.id, movieData.id)
    assertEquals(dummyDataMovie.name, movieData.name)
    assertEquals(dummyDataMovie.description, movieData.description)
    assertEquals(dummyDataMovie.poster, movieData.poster)
    assertEquals(dummyDataMovie.imgPreview, movieData.imgPreview)

    viewModel.getMovieDetail(movieId).observeForever(observer)
    verify(observer).onChanged(dummyDataMovie)
    }

    @Test
    fun testGetTvShowById() {
        val tvShowDummy = MutableLiveData<DataModel>()
        tvShowDummy.value = dummyDataTvShow

        Mockito.`when`(movieCatalogueRepository.getTvShowDetail(tvShowId)).thenReturn(tvShowDummy)

        val tvShowData = viewModel.getTvShowDetail(tvShowId).value as DataModel

        junit.framework.Assert.assertNotNull(tvShowData)
        assertEquals(dummyDataTvShow.id, tvShowData.id)
        assertEquals(dummyDataTvShow.name, tvShowData.name)
        assertEquals(dummyDataTvShow.description, tvShowData.description)
        assertEquals(dummyDataTvShow.poster, tvShowData.poster)
        assertEquals(dummyDataTvShow.imgPreview, tvShowData.imgPreview)

        viewModel.getTvShowDetail(tvShowId).observeForever(observer)
        verify(observer).onChanged(dummyDataTvShow)
    }
}