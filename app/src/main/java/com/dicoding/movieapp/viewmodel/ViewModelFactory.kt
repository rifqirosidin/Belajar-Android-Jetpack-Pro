package com.dicoding.movieapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.movieapp.di.Injection
import com.dicoding.movieapp.repository.MovieCatalogueRepository
import com.dicoding.movieapp.ui.home.detail.DetailViewModel
import com.dicoding.movieapp.ui.home.favorite.movie.FavoriteMovieViewModel
import com.dicoding.movieapp.ui.home.favorite.tvshow.FavoriteTvShowViewModel
import com.dicoding.movieapp.ui.home.fragment.movie.MovieViewModel
import com.dicoding.movieapp.ui.home.fragment.tv_show.TvShowViewModel

class ViewModelFactory private constructor(private val mCatalogRepository: MovieCatalogueRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideCatalogRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java)-> {
                FavoriteMovieViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java)-> {
                FavoriteTvShowViewModel(mCatalogRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}