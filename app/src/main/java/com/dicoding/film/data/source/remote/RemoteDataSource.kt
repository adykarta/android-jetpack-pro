package com.dicoding.film.data.source.remote


import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.source.remote.api.ApiCall
import com.dicoding.film.data.source.remote.api.ApiCallback
import com.dicoding.film.data.source.remote.api.ApiResponse
import com.dicoding.film.data.source.remote.response.FilmDetailResponse
import com.dicoding.film.data.source.remote.response.TvDetailResponse


class RemoteDataSource private constructor(private val remoteDataSource: ApiCall) {


    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: ApiCall): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllFilm(): LiveData<ApiResponse<List<FilmEntity>>> {

        val filmResults = MutableLiveData<ApiResponse<List<FilmEntity>>>()
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
                filmResults.postValue(ApiResponse.success(filmList))
            }

            override fun onCallError(throwable: Throwable) {
                Log.d("ERROR", throwable.message ?: "")
            }
        })
        return filmResults
    }

    fun getAllTvShow(): LiveData<ApiResponse<List<FilmEntity>>> {
        val tvResults = MutableLiveData<ApiResponse<List<FilmEntity>>>()
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
                tvResults.postValue(ApiResponse.success(tvList))
            }

            override fun onCallError(throwable: Throwable) {
                Log.d("ERROR",throwable.message?:"")

            }
        })
        return tvResults
    }

   fun getDetailFilm(id: Int): LiveData<ApiResponse<FilmEntity>> {
        val detailFilmResult = MutableLiveData<ApiResponse<FilmEntity>>()
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
                detailFilmResult.postValue(ApiResponse.success(film))
            }

            override fun onCallError(throwable: Throwable) {
                Log.d("ERROR",throwable.message?:"")
            }


        })
        return detailFilmResult
    }

    fun getDetailTv(id: Int): LiveData<ApiResponse<FilmEntity>> {
        val detailTvResult = MutableLiveData<ApiResponse<FilmEntity>>()
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
                detailTvResult.postValue(ApiResponse.success(tv))
            }

            override fun onCallError(throwable: Throwable) {
                Log.d("ERROR",throwable.message?:"")
            }

        })
        return detailTvResult
    }



}