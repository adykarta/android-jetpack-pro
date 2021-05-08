package com.dicoding.film.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.repository.FilmRepository

class TvShowFavoriteViewModel(private val filmRepository: FilmRepository): ViewModel() {
    fun getTvShowFavorite(): LiveData<List<FilmEntity>> = filmRepository.getFavoritedTvShow()
}