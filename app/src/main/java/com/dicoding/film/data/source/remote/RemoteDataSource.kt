package com.dicoding.film.data.source.remote

import android.os.Handler
import android.os.Looper
import com.dicoding.film.data.source.remote.response.FilmDetailResponse
import com.dicoding.film.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllFilm(callback: LoadFilmCallback) {
        handler.postDelayed({ callback.onAllFilmsReceived(jsonHelper.loadFilmList()) }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllTvShow(callback: LoadTvShowCallback) {
        handler.postDelayed({ callback.onAllTvShowsReceived(jsonHelper.loadTvShowList()) }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getDetailFilm(id:Int, callback:LoadFilmDetailCallback){
        handler.postDelayed({callback.onFilmDetailReceived(jsonHelper.loadDetailFilm(id))}, SERVICE_LATENCY_IN_MILLIS)
    }
    fun getDetailTvShow(id:Int, callback:LoadTvShowDetailCallback){
        handler.postDelayed({callback.onTvShowDetailReceived(jsonHelper.loadDetailTvShow(id))}, SERVICE_LATENCY_IN_MILLIS)
    }



    interface LoadFilmCallback {
        fun onAllFilmsReceived(filmResponses: List<FilmDetailResponse>)
    }
    interface LoadTvShowCallback {
        fun onAllTvShowsReceived(tvResponses: List<FilmDetailResponse>)
    }

    interface LoadFilmDetailCallback {
        fun onFilmDetailReceived(filmResponse: FilmDetailResponse)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvResponse: FilmDetailResponse)
    }

}