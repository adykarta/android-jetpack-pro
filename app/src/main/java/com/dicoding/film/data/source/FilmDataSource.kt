package com.dicoding.film.data.source

import androidx.lifecycle.LiveData
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.source.remote.api.ApiResponse
import com.dicoding.film.vo.Resource

interface FilmDataSource {
    fun getAllFilm(): LiveData<Resource<List<FilmEntity>>>

    fun getAllTvShow(): LiveData<Resource<List<FilmEntity>>>

    fun getDetailFilm(id: Int):LiveData<Resource<FilmEntity>>

    fun getDetailTv(id:Int):LiveData<Resource<FilmEntity>>

    fun getFavoritedFilm(): LiveData<List<FilmEntity>>

    fun getFavoritedTvShow(): LiveData<List<FilmEntity>>

    fun setFavoriteFilm(film: FilmEntity, state: Boolean)

    fun setFavoriteTvShow(tv: FilmEntity, state: Boolean)

}