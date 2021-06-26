 package com.dicoding.movieapp.data.source.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.movieapp.data.source.local.entity.MovieEntity
import com.dicoding.movieapp.data.source.local.entity.TvShowEntity
import com.dicoding.movieapp.data.source.local.room.CatalogDao

 class LocalDataSource (private val catalogDao: CatalogDao) {
     companion object {
         private var INSTANCE: LocalDataSource? = null

         fun getInstance(catalogDao: CatalogDao): LocalDataSource =
             INSTANCE ?: LocalDataSource(catalogDao)
     }

     fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = catalogDao.getMovies()

     fun getMovieById(id: Int): LiveData<MovieEntity> = catalogDao.getMovieById(id)

     fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = catalogDao.getFavoriteMovies()

     fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> = catalogDao.getTvShows()

     fun getTvShowById(id: Int): LiveData<TvShowEntity> = catalogDao.getTvShowById(id)

     fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> = catalogDao.getFavoriteTvShows()

     fun insertMovies(movies: List<MovieEntity>) = catalogDao.insertMovies(movies)

     fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
         movie.isFavorite = newState
         catalogDao.updateMovie(movie)
     }

     fun updateMovie(movie: MovieEntity, newState: Boolean) {
         movie.isFavorite = newState
         catalogDao.updateMovie(movie)
         Log.d("update_movie", movie.toString())
     }

     fun insertTvShows(tvShows: List<TvShowEntity>) = catalogDao.insertTvShows(tvShows)

     fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
         tvShow.isFavorite = newState
         catalogDao.updateTvShow(tvShow)
     }

     fun updateTvShow(tvShow: TvShowEntity, newState: Boolean) {
         tvShow.isFavorite = newState
         catalogDao.updateTvShow(tvShow)
     }
     
}