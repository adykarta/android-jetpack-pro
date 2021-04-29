package com.dicoding.film.data

data class FilmEntity(
    var id: Int,
    var title: String,
    var genre: String,
    var overview: String,
    var userScore: Double,
    var releaseYear: String,
    var duration:Int,
    var photo:String
)