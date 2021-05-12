import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.repository.FakeFilmRepository
import com.dicoding.film.data.source.local.LocalDataSource
import com.dicoding.film.data.source.remote.RemoteDataSource
import com.dicoding.film.utils.AppExecutors
import com.dicoding.film.utils.DataDummy
import com.dicoding.film.utils.LiveDataTestUtil
import com.dicoding.film.utils.PagedListUtil
import com.dicoding.film.vo.Resource
import com.nhaarman.mockitokotlin2.doNothing
import org.mockito.Mockito.mock

import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`

class FilmRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val filmRepository = FakeFilmRepository(remote,local,appExecutors)
    private val filmResponses = DataDummy.generateDummyRemoteFilm()
    private val filmId = filmResponses[0].id
    private val tvResponses = DataDummy.generateDummyRemoteTvShows()
    private val tvId = tvResponses[0].id



    @Test
    fun getAllFilm() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        `when`(local.getAllFilm()).thenReturn(dataSourceFactory)
        filmRepository.getAllFilm()

        val filmEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyFilm()))
        verify(local).getAllFilm()
        assertNotNull(filmEntities.data)
        assertEquals(filmResponses.size.toLong(), filmEntities.data?.size?.toLong())

    }
    @Test
    fun getAllTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        `when`(local.getAllTvShow()).thenReturn(dataSourceFactory)
        filmRepository.getAllTvShow()

        val filmEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getAllTvShow()
        assertNotNull(filmEntities.data)
        assertEquals(tvResponses.size.toLong(), filmEntities.data?.size?.toLong())
    }

    @Test
    fun getAllFavoriteFilm() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        `when`(local.getFavoritedFilm()).thenReturn(dataSourceFactory)
        filmRepository.getFavoritedFilm()
        val filmEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyFavoriteFilm()))
        verify(local).getFavoritedFilm()
        assertNotNull(filmEntities)
        assertEquals(filmResponses.size.toLong(), filmEntities.data?.size?.toLong())
    }

    @Test
    fun getAllFavoriteTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        `when`(local.getFavoritedTvShow()).thenReturn(dataSourceFactory)
        filmRepository.getFavoritedTvShow()
        val filmEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyFavoriteTvShows()))
        verify(local).getFavoritedTvShow()
        assertNotNull(filmEntities)
        assertEquals(tvResponses.size.toLong(), filmEntities.data?.size?.toLong())
    }


    @Test
    fun getDetailFilm() {
        val dummyEntity = MutableLiveData<FilmEntity>()
        val dummyDetailFilm =  DataDummy.generateDummyFilm()[0]
        dummyEntity.value = dummyDetailFilm
        `when`<LiveData<FilmEntity>>(local.getDetailFilm(filmId)).thenReturn(dummyEntity)
        val filmEntitiesContent = LiveDataTestUtil.getValue(filmRepository.getDetailFilm(filmId))
        verify(local).getDetailFilm(filmId)
        assertNotNull(filmEntitiesContent)
        assertNotNull(filmEntitiesContent.data?.overview)
        assertEquals(dummyDetailFilm.overview, filmEntitiesContent.data?.overview)

    }

    @Test
    fun getDetailTv() {
        val dummyEntity = MutableLiveData<FilmEntity>()
        val dummyDetailTvShow =  DataDummy.generateDummyTvShows()[0]
        dummyEntity.value = dummyDetailTvShow
        `when`<LiveData<FilmEntity>>(local.getDetailFilm(tvId)).thenReturn(dummyEntity)
        val tvEntitiesContent = LiveDataTestUtil.getValue(filmRepository.getDetailTv(tvId))
        verify(local).getDetailFilm(tvId)
        assertNotNull(tvEntitiesContent)
        assertNotNull(tvEntitiesContent.data?.overview)
        assertEquals(dummyDetailTvShow.overview, tvEntitiesContent.data?.overview)
    }

    @Test
    fun setFavoriteUnFavoriteFilm() {
        val dummyFilm = DataDummy.generateDummyFilm()[0]
        doNothing().`when`(local).setFilmFavorite(dummyFilm,!dummyFilm.favorited)
        filmRepository.setFavoriteFilm(dummyFilm,!dummyFilm.favorited)
        verify(local).setFilmFavorite(dummyFilm,!dummyFilm.favorited)
        assertEquals(true, dummyFilm.favorited)


        }

}


