package com.dicoding.film.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.repository.FilmRepository

class DetailFilmViewModel(private val filmRepository: FilmRepository): ViewModel() {
    private var filmId: Int = 0

    fun setSelectedFilm(filmId: Int) {
        this.filmId = filmId
    }

    fun getFilm(): LiveData<FilmEntity> = filmRepository.getDetailFilm(filmId)
    fun getTvShow():  LiveData<FilmEntity> = filmRepository.getDetailTv(filmId)


}