package com.dicoding.film.ui.film

import androidx.lifecycle.ViewModel
import com.dicoding.film.data.FilmEntity
import com.dicoding.film.utils.DataDummy

class FilmViewModel: ViewModel() {
    fun getFilm(): List<FilmEntity> = DataDummy.generateDummyFilm()
}