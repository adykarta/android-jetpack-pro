package com.dicoding.film.data.source.remote.response
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmDetailResponse(
    @field:SerializedName("id")
    var id: Int,
    @field:SerializedName("title")
    var title: String? = "",
    @field:SerializedName("genres")
    var genre: ArrayList<GenreResponse> = ArrayList<GenreResponse>() ,
    @field:SerializedName("overview")
    var overview: String ="",
    @field:SerializedName("vote_average")
    var userScore: Double = 0.0,
    @field:SerializedName("release_date")
    var releaseYear: String?="",
    @field:SerializedName("runtime")
    var duration:Int? =0,
    @field:SerializedName("poster_path")
    var photo:String
): Parcelable
