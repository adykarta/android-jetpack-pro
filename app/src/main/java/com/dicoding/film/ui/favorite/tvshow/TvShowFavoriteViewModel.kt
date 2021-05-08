package com.dicoding.film.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.repository.FilmRepository

class TvShowFavoriteViewModel(private val filmRepository: FilmRepository): ViewModel() {
    fun getTvShowFavorite(): LiveData<PagedList<FilmEntity>> = filmRepository.getFavoritedTvShow()

    fun setFavorite(filmEntity: FilmEntity) {
        val newState = !filmEntity.favorited
        filmRepository.setFavoriteFilm(filmEntity, newState)
    }
}