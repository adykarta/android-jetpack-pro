package com.dicoding.film.data.repository

import androidx.lifecycle.LiveData
import com.dicoding.film.data.NetworkBoundResource
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.source.FilmDataSource
import com.dicoding.film.data.source.local.LocalDataSource
import com.dicoding.film.data.source.remote.RemoteDataSource
import com.dicoding.film.data.source.remote.api.ApiResponse
import com.dicoding.film.utils.AppExecutors
import com.dicoding.film.vo.Resource

class FilmRepository private constructor(private val remoteDataSource: RemoteDataSource,
                                         private val localDataSource: LocalDataSource,
                                         private val appExecutors: AppExecutors
) :
    FilmDataSource {

    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): FilmRepository =
            instance
                ?: synchronized(this) {
                instance
                    ?: FilmRepository(
                        remoteData, localData,appExecutors
                    )
                        .apply { instance = this }
            }
    }



    override fun getAllFilm(): LiveData<Resource<List<FilmEntity>>> {
        return object : NetworkBoundResource<List<FilmEntity>, List<FilmEntity>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<FilmEntity>> =
                localDataSource.getAllFilm()

            override fun shouldFetch(data: List<FilmEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<FilmEntity>>> =
                remoteDataSource.getAllFilm()

            override fun saveCallResult(data: List<FilmEntity>) {
                localDataSource.insertFilm(data)
            }

        }.asLiveData()

    }

    override fun getAllTvShow(): LiveData<Resource<List<FilmEntity>>> {
        return object : NetworkBoundResource<List<FilmEntity>, List<FilmEntity>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<FilmEntity>> =
                localDataSource.getAllTvShow()

            override fun shouldFetch(data: List<FilmEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<FilmEntity>>> =
                remoteDataSource.getAllTvShow()

            override fun saveCallResult(data: List<FilmEntity>) {
                localDataSource.insertFilm(data)
            }

        }.asLiveData()
    }



    override fun getDetailFilm(id: Int): LiveData<Resource<FilmEntity>> {
        return object : NetworkBoundResource<FilmEntity, FilmEntity>(appExecutors) {
            public override fun loadFromDB(): LiveData<FilmEntity> =
                localDataSource.getDetailFilm(id)

            override fun shouldFetch(data: FilmEntity?): Boolean =
                data == null

            public override fun createCall(): LiveData<ApiResponse<FilmEntity>> =
                remoteDataSource.getDetailFilm(id)

            override fun saveCallResult(data: FilmEntity) {
                localDataSource.updateFilm(data)
            }


        }.asLiveData()
    }

    override fun getDetailTv(id: Int): LiveData<Resource<FilmEntity>> {
        return object : NetworkBoundResource<FilmEntity, FilmEntity>(appExecutors) {
            public override fun loadFromDB(): LiveData<FilmEntity> =
                localDataSource.getDetailFilm(id)

            override fun shouldFetch(data: FilmEntity?): Boolean =
                data == null

            public override fun createCall(): LiveData<ApiResponse<FilmEntity>> =
                remoteDataSource.getDetailTv(id)

            override fun saveCallResult(data: FilmEntity) {
                localDataSource.updateFilm(data)
            }


        }.asLiveData()
    }

    override fun getFavoritedFilm(): LiveData<List<FilmEntity>> = localDataSource.getFavoritedFilm()

    override fun getFavoritedTvShow(): LiveData<List<FilmEntity>> = localDataSource.getFavoritedTvShow()

    override fun setFavoriteFilm(film: FilmEntity, state: Boolean)  = appExecutors.diskIO().execute { localDataSource.setFilmFavorite(film,state) }
    override fun setFavoriteTvShow(tv: FilmEntity, state: Boolean) = appExecutors.diskIO().execute { localDataSource.setFilmFavorite(tv,state) }
}