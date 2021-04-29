package com.dicoding.film.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.film.data.FilmEntity
import com.dicoding.film.data.source.remote.RemoteDataSource
import com.dicoding.film.data.source.remote.response.FilmDetailResponse

class FilmRepository private constructor(private val remoteDataSource: RemoteDataSource) : FilmDataSource {

    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(remoteData: RemoteDataSource): FilmRepository =
            instance ?: synchronized(this) {
                instance ?: FilmRepository(remoteData).apply { instance = this }
            }
    }



    override fun getAllFilm(): LiveData<List<FilmEntity>> {
        val filmResults = MutableLiveData<List<FilmEntity>>()
        remoteDataSource.getAllFilm(object : RemoteDataSource.LoadFilmCallback {

            override fun onAllFilmsReceived(filmResponses: List<FilmDetailResponse>) {
                Log.d("TAG","filmlistresponse"+filmResponses.toString())
                val filmList = ArrayList<FilmEntity>()

                for (response in filmResponses) {
                    val film = FilmEntity(
                        response.id,
                        response.title?:"",
                        response.genre,
                        response.overview,
                        response.userScore,
                        response.releaseYear?:"",
                        response.duration?:0,
                        response.photo

                      )
                    filmList.add(film)
                }
                Log.d("TAG","filmlistini"+filmList.toString())
               filmResults.postValue(filmList)
            }
        })

        return filmResults
    }

    override fun getAllTvShow(): LiveData<List<FilmEntity>> {
        val tvResults = MutableLiveData<List<FilmEntity>>()
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowsReceived(tvResponses: List<FilmDetailResponse>) {
                val tvList = ArrayList<FilmEntity>()
                for (response in tvResponses) {
                    val tv = FilmEntity(
                        response.id,
                        response.title?:"",
                        response.genre,
                        response.overview,
                        response.userScore,
                        response.releaseYear?:"",
                        response.duration?:0,
                        response.photo

                    )
                    tvList.add(tv)
                }
                tvResults.postValue(tvList)
            }
        })

        return tvResults
    }

    override fun getDetailFilm(id: Int): LiveData<FilmEntity> {
        val detailFilmResult = MutableLiveData<FilmEntity>()
        remoteDataSource.getDetailFilm(id, object : RemoteDataSource.LoadFilmDetailCallback {

            override fun onFilmDetailReceived(filmResponse: FilmDetailResponse) {
               var film: FilmEntity
                film = FilmEntity(
                    filmResponse.id,
                    filmResponse.title?:"",
                    filmResponse.genre,
                    filmResponse.overview,
                    filmResponse.userScore,
                    filmResponse.releaseYear?:"",
                    filmResponse.duration?:0,
                    filmResponse.photo
                )
               detailFilmResult.postValue(film)

            }
        })
        return detailFilmResult
    }

    override fun getDetailTv(id: Int): LiveData<FilmEntity> {
        val detailTvResult = MutableLiveData<FilmEntity>()
        remoteDataSource.getDetailTvShow(id, object : RemoteDataSource.LoadTvShowDetailCallback {

            override fun onTvShowDetailReceived(tvResponse: FilmDetailResponse) {
                var tv: FilmEntity
                tv = FilmEntity(
                    tvResponse.id,
                   tvResponse.title?:"",
                    tvResponse.genre,
                    tvResponse.overview,
                    tvResponse.userScore,
                   tvResponse.releaseYear?:"",
                    tvResponse.duration?:0,
                    tvResponse.photo
                )
                detailTvResult.postValue(tv)

            }
        })
        return detailTvResult
    }
}