package com.dicoding.film.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dicoding.film.data.model.FilmEntity

interface FilmDao {
    @Query("SELECT * FROM filmentities where type='film' ")
    fun getFilm(): LiveData<List<FilmEntity>>

    @Query("SELECT * FROM filmentities where type='film' and favorited = 1")
    fun getFavoritedFilm(): LiveData<List<FilmEntity>>

    @Query("SELECT * FROM filmentities where type='tvshow' ")
    fun getTvShow(): LiveData<List<FilmEntity>>

    @Query("SELECT * FROM filmentities where type='tvshow' and favorited = 1")
    fun getFavoritedTvShow(): LiveData<List<FilmEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(films: List<FilmEntity>)

    @Update
    fun updateFilm(film: FilmEntity)

    @Query("SELECT * FROM filmentities WHERE id = :filmId")
    fun getDetail(filmId: Int): LiveData<FilmEntity>


}