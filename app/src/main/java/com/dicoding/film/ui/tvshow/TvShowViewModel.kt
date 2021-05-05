package com.dicoding.film.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.repository.FilmRepository


class TvShowViewModel(private val filmRepository: FilmRepository): ViewModel() {
    fun getTvShow(): LiveData<List<FilmEntity>> = filmRepository.getAllTvShow()
}