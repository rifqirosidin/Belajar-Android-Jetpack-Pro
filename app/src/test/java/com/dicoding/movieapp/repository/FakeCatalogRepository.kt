package com.dicoding.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.movieapp.data.source.NetworkBoundResource
import com.dicoding.movieapp.data.source.local.LocalDataSource
import com.dicoding.movieapp.data.source.local.entity.MovieEntity
import com.dicoding.movieapp.data.source.local.entity.TvShowEntity
import com.dicoding.movieapp.data.source.remote.response.*
import com.dicoding.movieapp.utils.AppExecutors
import com.dicoding.movieapp.utils.Resource

class FakeCatalogRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): CatalogRepository{

    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<Movie>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        id = response.id,
                        title = response.name.toString(),
                        backdropPath = response.imgPreview.toString(),
                        overview = response.description.toString(),
                        posterPath = response.poster.toString(),
                        isFavorite = false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<MovieEntity> = localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data != null && data.title == ""

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getDetailMovie(movieId)

            override fun saveCallResult(data: MovieDetailResponse) {
                val movie = MovieEntity(
                    id = data.id,
                    title = data.title,
                    backdropPath = data.backdropPath,
                    overview = data.overview,
                    posterPath = data.posterPath,
                    isFavorite = false
                )
                localDataSource.updateMovie(movie, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        localDataSource.setFavoriteMovie(movie, state)
    }

    override fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShow>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<TvShow>) {
                val movieList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        id = response.id,
                        title = response.name.toString(),
                        backdropPath = response.imgPreview.toString(),
                        overview = response.description.toString(),
                        posterPath = response.poster.toString(),
                        isFavorite = false
                    )
                    movieList.add(tvShow)
                }
                localDataSource.insertTvShows(movieList)
            }
        }.asLiveData()

    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowDetailResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<TvShowEntity> = localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data != null && data.title == ""

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getDetailTvShow(tvShowId)

            override fun saveCallResult(data: TvShowDetailResponse) {
                val tvShow = TvShowEntity(
                    id = data.id,
                    title = data.name,
                    backdropPath = data.backdropPath,
                    overview = data.overview,
                    posterPath = data.posterPath,
                    isFavorite = false
                )
                localDataSource.updateTvShow(tvShow, false)
            }
        }.asLiveData()

    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        localDataSource.setFavoriteTvShow(tvShow, state)
    }


}