package com.dicoding.film.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.film.data.model.FilmEntity

@Dao
interface FilmDao {
    @Query("SELECT * FROM filmentities where type='film' ")
    fun getFilm(): DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM filmentities where type='film' and favorited = 1")
    fun getFavoritedFilm():  DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM filmentities where type='tvshow' ")
    fun getTvShow():  DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM filmentities where type='tvshow' and favorited = 1")
    fun getFavoritedTvShow(): DataSource.Factory<Int, FilmEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(films: List<FilmEntity>)

    @Update
    fun updateFilm(film: FilmEntity)

    @Query("SELECT * FROM filmentities WHERE id = :filmId")
    fun getDetail(filmId: Int): LiveData<FilmEntity>


}