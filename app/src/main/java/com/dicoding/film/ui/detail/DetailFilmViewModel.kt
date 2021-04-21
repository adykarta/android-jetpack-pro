package com.dicoding.film.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.film.data.FilmEntity
import com.dicoding.film.utils.DataDummy

class DetailFilmViewModel: ViewModel() {
    private lateinit var filmTitle: String

    fun setSelectedFilm(filmTitle: String) {
        this.filmTitle = filmTitle
    }


    fun getFilm(): FilmEntity {
        lateinit var film: FilmEntity
        val filmEntities = DataDummy.generateDummyFilm()
        for (filmEntity in filmEntities) {
            if (filmEntity.title == filmTitle) {
                film = filmEntity
            }
        }
        return film
    }
    fun getTvShow(): FilmEntity {
        lateinit var tvshow: FilmEntity
        val tvshowEntities = DataDummy.generateDummyTvShows()
        for (tvshowEntity in tvshowEntities) {
            if (tvshowEntity.title == filmTitle) {
                tvshow = tvshowEntity
            }
        }
        return tvshow
    }


}