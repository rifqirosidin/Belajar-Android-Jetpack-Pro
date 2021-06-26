package com.dicoding.movieapp.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.movieapp.data.source.local.entity.MovieEntity
import com.dicoding.movieapp.data.source.local.entity.TvShowEntity

@Dao
interface CatalogDao {

    @Query("SELECT * FROM movie_entities")
    fun getMovies() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show_entities")
    fun getTvShows() : DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movie_entities WHERE isFavorite = 1")
    fun getFavoriteMovies() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show_entities WHERE isFavorite = 1")
    fun getFavoriteTvShows() : DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movie_entities WHERE id = :movieId")
    fun getMovieById(movieId: Int) : LiveData<MovieEntity>

    @Query("SELECT * FROM tv_show_entities WHERE id = :tvShowId")
    fun getTvShowById(tvShowId: Int) : LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update(entity = MovieEntity::class)
    fun updateMovie(movie : MovieEntity)

    @Update(entity = TvShowEntity::class)
    fun updateTvShow(tvShows: TvShowEntity)
}