package com.dicoding.film.data.source.remote.api

import com.dicoding.film.BuildConfig
import com.dicoding.film.data.source.remote.response.FilmDetailResponse
import com.dicoding.film.data.source.remote.response.FilmListResponse
import com.dicoding.film.data.source.remote.response.TvDetailResponse
import com.dicoding.film.data.source.remote.response.TvListResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("movie/popular?api_key=${BuildConfig.FILM_TOKEN}&language=en-US")
    fun getFilmList(): Call<FilmListResponse>

    @GET("movie/{id}?api_key=${BuildConfig.FILM_TOKEN}&language=en-US")
    fun getFilm( @Path("id") id: Int): Call<FilmDetailResponse>

    @GET("tv/popular?api_key=${BuildConfig.FILM_TOKEN}&language=en-US")
    fun getTvShowList(): Call<TvListResponse>

    @GET("tv/{id}?api_key=${BuildConfig.FILM_TOKEN}&language=en-US")
    fun getTvShow( @Path("id") id: Int): Call<TvDetailResponse>

}