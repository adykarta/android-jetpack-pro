package com.dicoding.film.di

import com.dicoding.film.data.repository.FilmRepository
import com.dicoding.film.data.source.remote.api.ApiCall

object Injection {
    fun provideRepository(): FilmRepository {
        val remoteDataSource = ApiCall.getInstance()
        return FilmRepository.getInstance(remoteDataSource)
    }
}