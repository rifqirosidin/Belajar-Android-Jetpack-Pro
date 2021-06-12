package com.dicoding.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.movieapp.data.source.remote.response.*
import com.dicoding.movieapp.model.DataModel

class MovieCatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource): CatalogRepository {

    companion object {
        @Volatile
        private var instance: MovieCatalogueRepository? = null
        fun getInstance(remoteDataSource: RemoteDataSource): MovieCatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: MovieCatalogueRepository(remoteDataSource)
            }
    }

    override fun getMovies(): LiveData<List<DataModel>> {
        val movieResult = MutableLiveData<List<DataModel>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movie>?) {
                val movieList = ArrayList<DataModel>()
                if (movies != null){
                    for (res in movies){
                        with(res){
                            val movie = DataModel(id, name, description, poster, imgPreview)
                            movieList.add(movie)
                        }
                    }
                    movieResult.postValue(movieList)
                }
            }
        })
        return movieResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<DataModel> {
        val movieResult = MutableLiveData<DataModel>()
        remoteDataSource.getMovieDetail(object : RemoteDataSource.LoadMovieDetailCallback{
            override fun onMovieDetailLoaded(movieDetail: MovieDetailResponse?) {
                if (movieDetail != null){
                    with(movieDetail){
                        val movie = DataModel(
                            id = id,
                            name = title,
                            description = overview,
                            poster = posterPath,
                            imgPreview = backdropPath
                        )
                        movieResult.postValue(movie)
                    }
                }
            }
        }, movieId)
        return movieResult
    }

    override fun getTvShows(): LiveData<List<DataModel>> {
        val tvShowResult = MutableLiveData<List<DataModel>>()
        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onTvShowsLoaded(tvShows: List<TvShow>?) {
                val tvShowList = ArrayList<DataModel>()
                if (tvShows != null) {
                    for (response in tvShows) {
                        with(response) {
                            val tvShow = DataModel(id, name, description, poster, imgPreview)
                            tvShowList.add(tvShow)
                        }
                    }
                    tvShowResult.postValue(tvShowList)
                }
            }
        })
        return tvShowResult
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<DataModel> {
        val tvShowResult = MutableLiveData<DataModel>()
        remoteDataSource.getTvShowDetail(object : RemoteDataSource.LoadTvShowDetailCallback{
            override fun onTvShowDetailLoaded(tvShowDetail: TvShowDetailResponse?) {
                if (tvShowDetail != null){
                    with(tvShowDetail){
                        val movie = DataModel(
                            id = id,
                            name = name,
                            description = overview,
                            poster = posterPath,
                            imgPreview = backdropPath
                        )
                        tvShowResult.postValue(movie)
                    }
                }
            }
        }, tvShowId)
        return tvShowResult
    }
}