package com.dicoding.film.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.source.local.room.FilmDao

class LocalDataSource private constructor(private val mFilmDao: FilmDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getAllFilm():  DataSource.Factory<Int, FilmEntity> = mFilmDao.getFilm()
    fun getAllTvShow():  DataSource.Factory<Int, FilmEntity> = mFilmDao.getTvShow()




    fun getFavoritedFilm():  DataSource.Factory<Int, FilmEntity> = mFilmDao.getFavoritedFilm()
    fun getFavoritedTvShow():  DataSource.Factory<Int, FilmEntity> = mFilmDao.getFavoritedTvShow()

    fun getDetailFilm(filmId: Int): LiveData<FilmEntity> =
        mFilmDao.getDetail(filmId)



    fun insertFilm(films: List<FilmEntity>) = mFilmDao.insertFilm(films)

    fun updateFilm(film:FilmEntity){
        mFilmDao.updateFilm(film)
    }

    fun setFilmFavorite(film: FilmEntity, newState: Boolean) {
        film.favorited = newState
        mFilmDao.updateFilm(film)
    }


}