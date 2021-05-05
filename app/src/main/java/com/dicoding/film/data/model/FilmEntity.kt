package com.dicoding.film.data.model
import com.dicoding.film.data.source.remote.response.GenreResponse
data class FilmEntity(
    var id: Int,
    var title: String,
    var genre:  ArrayList<GenreResponse>,
    var overview: String,
    var userScore: Double,
    var releaseYear: String,
    var duration:Int,
    var photo:String
)