package com.dicoding.film.data.source.remote.api

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dicoding.film.data.source.remote.response.*
import com.dicoding.film.utils.EspressoIdlingResource
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCall(private val context: Context) {

    fun loadFilmList(callback: ApiCallback<ArrayList<FilmDetailResponse>>){


        val list = ArrayList<FilmDetailResponse>()
        try {
            EspressoIdlingResource.increment()
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

                        callback.onCallSuccess(list)
                        EspressoIdlingResource.decrement()

                    } else {

                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<FilmListResponse>, t: Throwable) {
                    callback.onCallError(t)
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            })



        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }
    fun loadTvShowList(callback: ApiCallback<ArrayList<TvDetailResponse>>){
        val list = ArrayList<TvDetailResponse>()
        try {
            EspressoIdlingResource.increment()
            val client = ApiConfig.getApiService().getTvShowList()
            client.enqueue(object : Callback<TvListResponse> {
                override fun onResponse(
                    call: Call<TvListResponse>,
                    response: Response<TvListResponse>
                ) {
                    if (response.isSuccessful) {
                        val listOfFilm = response.body()?.allFilm ?: ArrayList<TvDetailResponse>()
                        for (i in listOfFilm) {
                            val poster = "https://image.tmdb.org/t/p/w500/" + i.photo

                            val film = TvDetailResponse(
                                id = i.id,
                                title = i.title,
                                releaseYear = i.releaseYear,
                                photo = poster)
                            list.add(film)


                        }
                        callback.onCallSuccess(list)
                        EspressoIdlingResource.decrement()

                    } else {
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<TvListResponse>, t: Throwable) {
                    callback.onCallError(t)
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            })

        } catch (e: JSONException) {
            e.printStackTrace()
        }


    }


    fun loadDetailFilm(id: Int,callback: ApiCallback<FilmDetailResponse>){
        var filmResponse: FilmDetailResponse? = null
        try {
            EspressoIdlingResource.increment()
            val client = ApiConfig.getApiService().getFilm(id)

            client.enqueue(object : Callback<FilmDetailResponse> {
                override fun onResponse(
                    call: Call<FilmDetailResponse>,
                    response: Response<FilmDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        val poster = "https://image.tmdb.org/t/p/w500/" + response.body()?.photo
                        val listGenre = ArrayList<GenreResponse>()
                        for(i in response.body()?.genre ?:ArrayList<GenreResponse>()){
                            val genre = GenreResponse(i.id, i.name)
                            listGenre.add(genre)

                        }
                        filmResponse = FilmDetailResponse(
                            response.body()?.id ?:0,
                            response.body()?.title ?:"",
                            listGenre ,
                            response.body()?.overview ?: "",
                            response.body()?.userScore ?:0.0,
                            response.body()?.releaseYear ?:"",
                            response.body()?.duration ?:0,
                            poster)
                        callback.onCallSuccess(filmResponse as FilmDetailResponse)
                        EspressoIdlingResource.decrement()

                    } else {
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<FilmDetailResponse>, t: Throwable) {
                    callback.onCallError(t)
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            })

        } catch (e: JSONException) {
            e.printStackTrace()
        }



    }

    fun loadDetailTvShow(id: Int,callback: ApiCallback<TvDetailResponse>){
        var filmResponse: TvDetailResponse? = null
        try {
            EspressoIdlingResource.increment()
            val client = ApiConfig.getApiService().getTvShow(id)
            client.enqueue(object : Callback<TvDetailResponse> {
                override fun onResponse(
                    call: Call<TvDetailResponse>,
                    response: Response<TvDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        val poster = "https://image.tmdb.org/t/p/w500/" + response.body()?.photo
                        val listGenre = ArrayList<GenreResponse>()
                        for(i in response.body()?.genre ?:ArrayList<GenreResponse>()){
                            val genre = GenreResponse(i.id, i.name)
                            listGenre.add(genre)

                        }
                        filmResponse = TvDetailResponse(
                            response.body()?.id ?:0,
                            response.body()?.title ?:"",
                            listGenre,
                            response.body()?.overview ?: "",
                            response.body()?.userScore ?: 0.0,
                            response.body()?.releaseYear ?:"",
                            response.body()?.duration ?:0,
                            poster)
                        callback.onCallSuccess(filmResponse as TvDetailResponse)
                        EspressoIdlingResource.decrement()
                    } else {
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                    callback.onCallError(t)
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            })

        } catch (e: JSONException) {
            e.printStackTrace()
        }



    }
}