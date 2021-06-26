package com.dicoding.movieapp.di

import android.content.Context
import com.dicoding.movieapp.data.source.local.LocalDataSource
import com.dicoding.movieapp.data.source.local.room.CatalogDatabase
import com.dicoding.movieapp.data.source.remote.response.RemoteDataSource
import com.dicoding.movieapp.repository.MovieCatalogueRepository
import com.dicoding.movieapp.utils.AppExecutors

object Injection {
    fun provideCatalogRepository(context: Context): MovieCatalogueRepository {
        val database = CatalogDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.catalogDao())
        val appExecutors = AppExecutors()
        return MovieCatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}