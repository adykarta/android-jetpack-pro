package com.dicoding.film.ui.favorite.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.repository.FilmRepository

class FilmFavoriteViewModel(private val filmRepository: FilmRepository): ViewModel() {
    fun getFavoriteFilm(): LiveData<List<FilmEntity>> = filmRepository.getFavoritedFilm()
}