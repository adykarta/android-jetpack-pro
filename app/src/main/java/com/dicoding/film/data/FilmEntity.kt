package com.dicoding.film.data

data class FilmEntity(
    var title: String,
    var genre: String,
    var overview: String,
    var userScore: Int,
    var releaseYear: Int,
    var duration:Int,
    var photo:Int
)