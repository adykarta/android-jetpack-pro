package com.dicoding.film.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.source.FilmDataSource
import com.dicoding.film.data.source.remote.api.ApiCall
import com.dicoding.film.data.source.remote.api.ApiCallback
import com.dicoding.film.data.source.remote.response.FilmDetailResponse
import com.dicoding.film.data.source.remote.response.TvDetailResponse


class FakeFilmRepository (private val remoteDataSource: ApiCall) : FilmDataSource {



    override fun getAllFilm(): LiveData<List<FilmEntity>> {
        val filmResults = MutableLiveData<List<FilmEntity>>()
        remoteDataSource.loadFilmList(object :
            ApiCallback<ArrayList<FilmDetailResponse>> {

            override fun onCallSuccess(filmResponses: ArrayList<FilmDetailResponse>) {
                val filmList = ArrayList<FilmEntity>()
                for (response in filmResponses) {
                    val film =
                        FilmEntity(
                            response.id,
                            response.title ?: "",
                            response.genre,
                            response.overview,
                            response.userScore,
                            response.releaseYear ?: "",
                            response.duration ?: 0,
                            response.photo,
                            false,
                            "film"

                        )
                    filmList.add(film)
                }
                filmResults.postValue(filmList)
            }

            override fun onCallError(throwable: Throwable) {
                Log.d("ERROR",throwable.message?:"")
            }
        })
        return filmResults
    }

    override fun getAllTvShow(): LiveData<List<FilmEntity>> {
        val tvResults = MutableLiveData<List<FilmEntity>>()
        remoteDataSource.loadTvShowList(object :
            ApiCallback<ArrayList<TvDetailResponse>> {
            override fun onCallSuccess(tvResponses: ArrayList<TvDetailResponse>) {
                val tvList = ArrayList<FilmEntity>()
                for (response in tvResponses) {

                    val tv =
                        FilmEntity(
                            response.id,
                            response.title ?: "",
                            response.genre,
                            response.overview,
                            response.userScore,
                            response.releaseYear ?: "",
                            response.duration ?: 0,
                            response.photo,
                            false,
                            "tvshow"

                        )
                    tvList.add(tv)
                }
                tvResults.postValue(tvList)
            }

            override fun onCallError(throwable: Throwable) {
                Log.d("ERROR",throwable.message?:"")

            }
        })

        return tvResults
    }

    override fun getDetailFilm(id: Int): LiveData<FilmEntity> {
        val detailFilmResult = MutableLiveData<FilmEntity>()
        remoteDataSource.loadDetailFilm(id, object :
            ApiCallback<FilmDetailResponse> {
            override fun onCallSuccess(filmResponse: FilmDetailResponse) {
                var film: FilmEntity
                film = FilmEntity(
                    filmResponse.id,
                    filmResponse.title ?: "",
                    filmResponse.genre,
                    filmResponse.overview,
                    filmResponse.userScore,
                    filmResponse.releaseYear ?: "",
                    filmResponse.duration ?: 0,
                    filmResponse.photo,
                    false,
                    "film"
                )
                detailFilmResult.postValue(film)
            }

            override fun onCallError(throwable: Throwable) {
                Log.d("ERROR",throwable.message?:"")
            }


        })
        return detailFilmResult
    }

    override fun getDetailTv(id: Int): LiveData<FilmEntity> {
        val detailTvResult = MutableLiveData<FilmEntity>()
        remoteDataSource.loadDetailTvShow(id, object :
            ApiCallback<TvDetailResponse> {
            override fun onCallSuccess(tvResponse: TvDetailResponse) {
                var tv: FilmEntity
                tv = FilmEntity(
                    tvResponse.id,
                    tvResponse.title ?: "",
                    tvResponse.genre,
                    tvResponse.overview,
                    tvResponse.userScore,
                    tvResponse.releaseYear ?: "",
                    tvResponse.duration ?: 0,
                    tvResponse.photo,
                    false,
                    "tvshow"
                )
                detailTvResult.postValue(tv)
            }

            override fun onCallError(throwable: Throwable) {
                Log.d("ERROR",throwable.message?:"")
            }

        })
        return detailTvResult
    }
}