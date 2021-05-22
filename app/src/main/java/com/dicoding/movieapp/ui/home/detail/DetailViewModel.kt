package com.dicoding.movieapp.ui.home.detail

import androidx.lifecycle.ViewModel
import com.dicoding.movieapp.model.DataModel
import com.dicoding.movieapp.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var movieId: String
    private lateinit var tvShowId: String

    private fun getMovieList(): ArrayList<DataModel> = DataDummy.createDataMovieDummy() as ArrayList<DataModel>
    private fun getTvShowList(): ArrayList<DataModel> = DataDummy.createDataTvShowDummy() as ArrayList<DataModel>

    fun setMovieId(movieId: String){
        this.movieId = movieId
    }

    fun setTvShowId(tvShowId: String){
        this.tvShowId = tvShowId
    }

    fun getMovieById(): DataModel {
        lateinit var result: DataModel
        val listMovie = getMovieList()
        for (movie in listMovie){
            if (movie.id == movieId){
                result = movie
                break
            }
        }
        return result
    }

    fun getTvShowById(): DataModel {
        lateinit var result: DataModel
        val listTvShow = getTvShowList()
        for (tvShow in listTvShow){
            if (tvShow.id == tvShowId){
                result = tvShow
                break
            }
        }
        return result
    }
}