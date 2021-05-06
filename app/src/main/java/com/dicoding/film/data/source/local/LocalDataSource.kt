package com.dicoding.film.data.source.local

import androidx.lifecycle.LiveData
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.source.local.room.FilmDao

class LocalDataSource private constructor(private val mFilmDao: FilmDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getAllFilm(): LiveData<List<FilmEntity>> = mFilmDao.getFilm()
    fun getAllTvShow(): LiveData<List<FilmEntity>> = mFilmDao.getTvShow()


    fun getFavoritedFilm(): LiveData<List<FilmEntity>> = mFilmDao.getFavoritedFilm()
    fun getFavoritedTvShow(): LiveData<List<FilmEntity>> = mFilmDao.getFavoritedTvShow()

    fun getDetailFilm(filmId: Int): LiveData<FilmEntity> =
        mFilmDao.getDetail(filmId)


    fun insertFilm(films: List<FilmEntity>) = mFilmDao.insertFilm(films)


    fun setFilmFavorite(film: FilmEntity, newState: Boolean) {
        film.favorited = newState
        mFilmDao.updateFilm(film)
    }


}