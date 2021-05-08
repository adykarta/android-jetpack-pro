package com.dicoding.film.data.model
import androidx.annotation.NonNull
import androidx.room.*
import com.dicoding.film.data.source.remote.response.GenreResponse
import com.dicoding.film.utils.DataConverter

@Entity(tableName = "filmentities")
data class FilmEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @TypeConverters(DataConverter::class)
    @ColumnInfo(name = "genre")
    var genre:  ArrayList<GenreResponse>,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "userScore")
    var userScore: Double,

    @ColumnInfo(name = "releaseYear")
    var releaseYear: String,

    @ColumnInfo(name = "duration")
    var duration:Int,

    @ColumnInfo(name = "photo")
    var photo:String,

    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false,

    @ColumnInfo(name = "type")
    var type: String
)