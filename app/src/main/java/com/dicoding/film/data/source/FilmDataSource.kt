package com.dicoding.film.data.source

import androidx.lifecycle.LiveData
import com.dicoding.film.data.FilmEntity

interface FilmDataSource {
    fun getAllFilm(): LiveData<List<FilmEntity>>

    fun getAllTvShow(): LiveData<List<FilmEntity>>

    fun getDetailFilm(id: Int):LiveData<FilmEntity>

    fun getDetailTv(id:Int):LiveData<FilmEntity>

}