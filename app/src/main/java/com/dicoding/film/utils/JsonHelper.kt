package com.dicoding.film.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.dicoding.film.data.source.remote.api.ApiConfig
import com.dicoding.film.data.source.remote.response.FilmDetailResponse
import com.dicoding.film.data.source.remote.response.FilmListResponse
import org.json.JSONException
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class JsonHelper(private val context: Context) {

    fun loadFilmList(): ArrayList<FilmDetailResponse> {
        val list = ArrayList<FilmDetailResponse>()
        try {
            val client = ApiConfig.getApiService().getFilmList()
            client.enqueue(object : Callback<FilmListResponse> {
                override fun onResponse(
                    call: Call<FilmListResponse>,
                    response: Response<FilmListResponse>
                ) {
                    if (response.isSuccessful) {

                        val listOfFilm = response.body()?.allFilm ?: ArrayList<FilmDetailResponse>()
                        for (i in listOfFilm) {
                            val poster = "https://image.tmdb.org/t/p/w500/" + i.photo
                            val film = FilmDetailResponse(
                                id = i.id,
                                title = i.title,
                                releaseYear = i.releaseYear,
                                photo = poster)
                            list.add(film)
                        }
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<FilmListResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })



        } catch (e: JSONException) {
            e.printStackTrace()
        }
        Log.d("TAG", "filmlista"+list)
        return list
    }
    fun loadTvShowList(): ArrayList<FilmDetailResponse> {
        val list = ArrayList<FilmDetailResponse>()
        try {
            val client = ApiConfig.getApiService().getTvShowList()
            client.enqueue(object : Callback<FilmListResponse> {
                override fun onResponse(
                    call: Call<FilmListResponse>,
                    response: Response<FilmListResponse>
                ) {
                    if (response.isSuccessful) {
                        val listOfFilm = response.body()?.allFilm ?: ArrayList<FilmDetailResponse>()
                        for (i in listOfFilm) {
                            val poster = "https://image.tmdb.org/t/p/w500/" + i.photo
                            val film = FilmDetailResponse(
                                id = i.id,
                                title = i.title,
                                releaseYear = i.releaseYear,
                                photo = poster)
                            list.add(film)

                        }

                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<FilmListResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }


    fun loadDetailFilm(id: Int): FilmDetailResponse {
        var filmResponse:FilmDetailResponse? = null
        try {
            val client = ApiConfig.getApiService().getFilm(id)
            client.enqueue(object : Callback<FilmDetailResponse> {
                override fun onResponse(
                    call: Call<FilmDetailResponse>,
                    response: Response<FilmDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        val poster = "https://image.tmdb.org/t/p/w500/" + response.body()?.photo
                        filmResponse = FilmDetailResponse(
                            response.body()?.id ?:0,
                            response.body()?.title ?:"",
                            response.body()?.genre ?:"",
                            response.body()?.overview ?: "",
                            response.body()?.userScore ?:0.0,
                            response.body()?.releaseYear ?:"",
                            response.body()?.duration ?:0,
                            poster)

                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<FilmDetailResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })

        } catch (e: JSONException) {
            e.printStackTrace()
        }


        return filmResponse as FilmDetailResponse
    }

    fun loadDetailTvShow(id: Int): FilmDetailResponse {
        var filmResponse:FilmDetailResponse? = null
        try {
            val client = ApiConfig.getApiService().getTvShow(id)
            client.enqueue(object : Callback<FilmDetailResponse> {
                override fun onResponse(
                    call: Call<FilmDetailResponse>,
                    response: Response<FilmDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        val poster = "https://image.tmdb.org/t/p/w500/" + response.body()?.photo
                        filmResponse = FilmDetailResponse(
                            response.body()?.id ?:0,
                            response.body()?.title ?:"",
                            response.body()?.genre ?:"",
                            response.body()?.overview ?: "",
                            response.body()?.userScore ?: 0.0,
                            response.body()?.releaseYear ?:"",
                            response.body()?.duration ?:0,
                            poster)

                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<FilmDetailResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })

        } catch (e: JSONException) {
            e.printStackTrace()
        }


        return filmResponse as FilmDetailResponse
    }
}