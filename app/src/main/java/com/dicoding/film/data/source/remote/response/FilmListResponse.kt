package com.dicoding.film.data.source.remote.response
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmListResponse (
    @field:SerializedName("results")
    var allFilm: ArrayList<FilmDetailResponse>,

):Parcelable
