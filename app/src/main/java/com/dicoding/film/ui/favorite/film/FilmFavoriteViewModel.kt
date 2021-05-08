package com.dicoding.film.ui.favorite.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.repository.FilmRepository

class FilmFavoriteViewModel(private val filmRepository: FilmRepository): ViewModel() {
    fun getFavoriteFilm(): LiveData<PagedList<FilmEntity>> = filmRepository.getFavoritedFilm()

    fun setFavorite(filmEntity: FilmEntity) {
        val newState = !filmEntity.favorited
        filmRepository.setFavoriteFilm(filmEntity, newState)
    }
}