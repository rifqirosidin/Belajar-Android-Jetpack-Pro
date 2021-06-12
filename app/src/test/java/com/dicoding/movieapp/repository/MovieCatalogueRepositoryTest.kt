package com.dicoding.movieapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.movieapp.LiveDataTestUtil
import com.dicoding.movieapp.data.source.remote.response.RemoteDataSource
import com.dicoding.movieapp.utils.DataDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieCatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val catalogRepository = FakeCatalogRepository(remote)

    private val movieListResponse = DataDummy.createDataMovieDummyResponse()
    private val movieId = movieListResponse[0].id
    private val tvShowListResponse = DataDummy.createDataTvShowDummyResponse()
    private val tvShowId = tvShowListResponse[0].id
    private val movieDetailResponse = DataDummy.createDataMovieDetailResponse()
    private val tvShowDetailResponse = DataDummy.createDataTvShowDetailResponse()

    @Test
    fun getMoviesTest() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onMoviesLoaded(movieListResponse)
            null
        }.`when`(remote).getMovies(any())

        val movies = LiveDataTestUtil.getValue(catalogRepository.getMovies())
        verify(remote).getMovies(any())
        org.junit.Assert.assertNotNull(movies)
        assertEquals(movieListResponse.size, movies.size)
    }

    @Test
    fun getMovieDetailTest() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieDetailCallback).onMovieDetailLoaded(movieDetailResponse)
            null
        }.`when`(remote).getMovieDetail(any(), eq(movieId))

        val movieDetail = LiveDataTestUtil.getValue(catalogRepository.getMovieDetail(movieId))
        verify(remote).getMovieDetail(any(), eq(movieId))
        org.junit.Assert.assertNotNull(movieDetail)
        assertEquals(movieDetailResponse.id, movieDetail.id)
    }

    @Test
    fun getTvShowsTest() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onTvShowsLoaded(tvShowListResponse)
            null
        }.`when`(remote).getTvShows(any())

        val tvShow = LiveDataTestUtil.getValue(catalogRepository.getTvShows())
        verify(remote).getTvShows(any())
        org.junit.Assert.assertNotNull(tvShow)
        assertEquals(tvShowListResponse.size, tvShow.size)
    }

    @Test
    fun getDetailTvShowTest() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowDetailCallback).onTvShowDetailLoaded(tvShowDetailResponse)
            null
        }.`when`(remote).getTvShowDetail(any(), eq(tvShowId))

        val tvShowDetail = LiveDataTestUtil.getValue(catalogRepository.getTvShowDetail(tvShowId))
        verify(remote).getTvShowDetail(any(), eq(tvShowId))
        Assert.assertNotNull(tvShowDetail)
        assertEquals(tvShowDetailResponse.id, tvShowDetail.id)
    }
}