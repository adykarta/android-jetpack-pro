package com.dicoding.film.di

import android.content.Context
import com.dicoding.film.data.source.FilmRepository
import com.dicoding.film.data.source.remote.RemoteDataSource
import com.dicoding.film.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): FilmRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return FilmRepository.getInstance(remoteDataSource)
    }
}