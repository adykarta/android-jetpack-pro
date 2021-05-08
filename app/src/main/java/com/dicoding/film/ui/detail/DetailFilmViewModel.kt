package com.dicoding.film.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.repository.FilmRepository
import com.dicoding.film.vo.Resource

class DetailFilmViewModel(private val filmRepository: FilmRepository): ViewModel() {
    val filmId = MutableLiveData<Int>()

    fun setSelectedFilm(filmId: Int) {
        this.filmId.value = filmId
    }

    var getFilm: LiveData<Resource<FilmEntity>> = Transformations.switchMap(filmId) { mFilmId ->
        filmRepository.getDetailFilm(mFilmId)
    }
    var getTvShow: LiveData<Resource<FilmEntity>> = Transformations.switchMap(filmId) { mFilmId ->
        filmRepository.getDetailTv(mFilmId)
    }

    fun setFavoriteFilm() {
        val filmResource = getFilm.value

        if (filmResource != null) {
            val film =filmResource.data
            if (film != null) {
                val newState = !film.favorited
                filmRepository.setFavoriteFilm(film, newState)
            }
        }
    }

    fun setFavoriteTv(){
        val filmResource = getTvShow.value
        if (filmResource != null) {
            val film =filmResource.data
            if (film != null) {
                val newState = !film.favorited
                filmRepository.setFavoriteFilm(film, newState)
            }
        }

    }






}