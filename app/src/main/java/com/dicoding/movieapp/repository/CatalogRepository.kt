package com.dicoding.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.movieapp.data.source.local.entity.MovieEntity
import com.dicoding.movieapp.data.source.local.entity.TvShowEntity
import com.dicoding.movieapp.utils.Resource

interface CatalogRepository {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}