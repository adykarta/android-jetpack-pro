package com.dicoding.film.di

import android.content.Context
import com.dicoding.film.data.repository.FilmRepository
import com.dicoding.film.data.source.local.LocalDataSource
import com.dicoding.film.data.source.local.room.FilmDatabase
import com.dicoding.film.data.source.remote.RemoteDataSource
import com.dicoding.film.data.source.remote.api.ApiCall
import com.dicoding.film.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): FilmRepository {

        val database = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiCall(context))
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()

        return FilmRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}