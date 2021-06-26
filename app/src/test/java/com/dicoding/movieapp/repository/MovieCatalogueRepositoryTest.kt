package com.dicoding.movieapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.movieapp.utils.LiveDataTestUtil
import com.dicoding.movieapp.data.source.local.LocalDataSource
import com.dicoding.movieapp.data.source.local.entity.MovieEntity
import com.dicoding.movieapp.data.source.local.entity.TvShowEntity
import com.dicoding.movieapp.data.source.remote.response.RemoteDataSource
import com.dicoding.movieapp.utils.AppExecutors
import com.dicoding.movieapp.utils.DataDummy
import com.dicoding.movieapp.utils.PagedListUtil
import com.dicoding.movieapp.utils.Resource
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieCatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val catalogRepository = FakeCatalogRepository(remote, local, appExecutors)

    private val movieListResponse = DataDummy.createDataMovieDummyResponse()
    private val movieId = movieListResponse[0].id
    private val tvShowListResponse = DataDummy.createDataTvShowDummyResponse()
    private val tvShowId = tvShowListResponse[0].id
    private val movieDetailResponse = DataDummy.createDataMovieDetailResponse()
    private val tvShowDetailResponse = DataDummy.createDataTvShowDetailResponse()

    @Test
    fun getMoviesTest() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getDummyMovies()))
        verify(local).getAllMovies()
        Assert.assertNotNull(movieEntities)
        assertEquals(movieListResponse.size, movieEntities.data?.size)
    }

    @Test
    fun getMovieDetailTest() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = DataDummy.getDummyDetailMovie()
        `when`(local.getMovieById(movieId)).thenReturn(dummyDetail)
        val movieDetailEntity = LiveDataTestUtil.getValue(catalogRepository.getDetailMovie(movieId))
        verify(local).getMovieById(movieId)
        Assert.assertNotNull(movieDetailEntity)
        assertEquals(movieDetailResponse.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteMovieTest() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getFavoriteMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getDummyMovies()))
        verify(local).getFavoriteMovies()
        Assert.assertNotNull(movieEntities)
        assertEquals(movieListResponse.size, movieEntities.data?.size)
    }

    @Test
    fun setFavoriteMovieTest() {
        catalogRepository.setFavoriteMovie(DataDummy.getDummyDetailMovie(), true)
        verify(local).setFavoriteMovie(DataDummy.getDummyDetailMovie(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getTvShowsTest() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        catalogRepository.getTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getDummyTvShows()))
        verify(local).getAllTvShows()
        Assert.assertNotNull(tvShowEntities)
        assertEquals(tvShowListResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun getDetailTvShowTest() {
        val dummyDetail = MutableLiveData<TvShowEntity>()
        dummyDetail.value = DataDummy.getDummyDetailTvShow()
        `when`(local.getTvShowById(tvShowId)).thenReturn(dummyDetail)

        val tvShowDetailEntity = LiveDataTestUtil.getValue(catalogRepository.getDetailTvShow(tvShowId))
        verify(local).getTvShowById(tvShowId)
        Assert.assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetailResponse.id, tvShowDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteTvShowTest() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        catalogRepository.getFavoriteTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getDummyTvShows()))
        verify(local).getFavoriteTvShows()
        Assert.assertNotNull(tvShowEntities)
        assertEquals(tvShowListResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun setFavoriteTvShowTest() {
        catalogRepository.setFavoriteTvShow(DataDummy.getDummyDetailTvShow(), true)
        verify(local).setFavoriteTvShow(DataDummy.getDummyDetailTvShow(), true)
        verifyNoMoreInteractions(local)
    }
}