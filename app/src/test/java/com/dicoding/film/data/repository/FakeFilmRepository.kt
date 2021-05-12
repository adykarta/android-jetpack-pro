package com.dicoding.film.data.repository


import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.film.data.NetworkBoundResource
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.source.FilmDataSource
import com.dicoding.film.data.source.local.LocalDataSource
import com.dicoding.film.data.source.remote.RemoteDataSource
import com.dicoding.film.data.source.remote.api.ApiResponse
import com.dicoding.film.utils.AppExecutors
import com.dicoding.film.vo.Resource


class FakeFilmRepository constructor(private val remoteDataSource: RemoteDataSource,
                                     private val localDataSource: LocalDataSource,
                                     private val appExecutors: AppExecutors
) : FilmDataSource {


    override fun getAllFilm(): LiveData<Resource<PagedList<FilmEntity>>> {
        return object : NetworkBoundResource<PagedList<FilmEntity>, List<FilmEntity>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<FilmEntity>>
            {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder( localDataSource.getAllFilm(), config).build()
            }


            override fun shouldFetch(data: PagedList<FilmEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<FilmEntity>>> =
                remoteDataSource.getAllFilm()

            override fun saveCallResult(data: List<FilmEntity>) {
                localDataSource.insertFilm(data)
            }

        }.asLiveData()

    }

    override fun getAllTvShow(): LiveData<Resource<PagedList<FilmEntity>>> {
        return object : NetworkBoundResource<PagedList<FilmEntity>, List<FilmEntity>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<FilmEntity>>
            {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(  localDataSource.getAllTvShow(), config).build()
            }


            override fun shouldFetch(data: PagedList<FilmEntity>?): Boolean =
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
                data?.overview ==""

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
                data?.overview ==""

            public override fun createCall(): LiveData<ApiResponse<FilmEntity>> =
                remoteDataSource.getDetailTv(id)

            override fun saveCallResult(data: FilmEntity) {
                localDataSource.updateFilm(data)
            }


        }.asLiveData()
    }

    override fun getFavoritedFilm(): LiveData<PagedList<FilmEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedFilm(), config).build()
    }

    override fun getFavoritedTvShow(): LiveData<PagedList<FilmEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedTvShow(), config).build()
    }

    override fun setFavoriteFilm(film: FilmEntity, state: Boolean)  = appExecutors.diskIO().execute {
        localDataSource.setFilmFavorite(film,state)
    }
    override fun setFavoriteTvShow(tv: FilmEntity, state: Boolean) = appExecutors.diskIO().execute { localDataSource.setFilmFavorite(tv,state) }
}