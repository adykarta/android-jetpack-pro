package com.dicoding.film.ui.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.film.data.FilmEntity
import com.dicoding.film.data.source.FilmRepository


class FilmViewModel(private val filmRepository: FilmRepository): ViewModel() {
    fun getFilm(): LiveData<List<FilmEntity>> = filmRepository.getAllFilm()
}