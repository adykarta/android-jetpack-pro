package com.dicoding.film.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.repository.FilmRepository
import com.dicoding.film.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailFilmViewModelTest {
    private lateinit var viewModel: DetailFilmViewModel
    private lateinit var viewModelTv: DetailFilmViewModel
    private val dummyFilm = DataDummy.generateDummyFilm()[0]
    private val filmId = dummyFilm.id
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val tvId = dummyTvShow.id


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository


    @Mock
    private lateinit var filmObserver: Observer<FilmEntity>

    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel(filmRepository)

        viewModel.setSelectedFilm(filmId)
        viewModelTv = DetailFilmViewModel(filmRepository)
        viewModelTv.setSelectedFilm(tvId)
    }

    @Test
    fun getFilm() {
        val film = MutableLiveData<FilmEntity>()
        film.value = dummyFilm

        `when`(filmRepository.getDetailFilm(filmId)).thenReturn(film)
        val filmEntity = viewModel.getFilm().value as FilmEntity
        verify(filmRepository).getDetailFilm(filmId)
        assertNotNull(filmEntity)
        assertEquals(dummyFilm.id, filmEntity.id)
        assertEquals(dummyFilm.title,filmEntity.title)
        assertEquals(dummyFilm.genre,filmEntity.genre)
        assertEquals(dummyFilm.overview,filmEntity.overview)
        assertEquals(dummyFilm.duration,filmEntity.duration)
        assertEquals(dummyFilm.releaseYear,filmEntity.releaseYear)
        assertEquals(dummyFilm.userScore,filmEntity.userScore,0.0)
        assertEquals(dummyFilm.photo,filmEntity.photo)
        viewModel.getFilm().observeForever(filmObserver)
        verify(filmObserver).onChanged(dummyFilm)
    }
    @Test
    fun getTvShow() {
        val tv = MutableLiveData<FilmEntity>()
        tv.value = dummyTvShow

        `when`(filmRepository.getDetailTv(tvId)).thenReturn(tv)
        val filmEntity = viewModel.getTvShow().value as FilmEntity
        verify(filmRepository).getDetailTv(tvId)
        assertNotNull(filmEntity)
        assertEquals(dummyTvShow.id, filmEntity.id)
        assertEquals(dummyTvShow.title,filmEntity.title)
        assertEquals(dummyTvShow.genre,filmEntity.genre)
        assertEquals(dummyTvShow.overview,filmEntity.overview)
        assertEquals(dummyTvShow.duration,filmEntity.duration)
        assertEquals(dummyTvShow.releaseYear,filmEntity.releaseYear)
        assertEquals(dummyTvShow.userScore,filmEntity.userScore,0.0)
        assertEquals(dummyTvShow.photo,filmEntity.photo)
        viewModel.getTvShow().observeForever(filmObserver)
        verify(filmObserver).onChanged(dummyTvShow)
    }


}