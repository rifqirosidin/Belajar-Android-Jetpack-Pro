package com.dicoding.movieapp.di

import com.dicoding.movieapp.data.source.remote.response.RemoteDataSource
import com.dicoding.movieapp.repository.CatalogRepository
import com.dicoding.movieapp.repository.MovieCatalogueRepository

object Injection {
    fun provideCatalogRepository(): MovieCatalogueRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieCatalogueRepository.getInstance(remoteDataSource)
    }
}