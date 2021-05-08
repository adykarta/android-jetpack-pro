package com.dicoding.film.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.repository.FilmRepository
import com.dicoding.film.utils.DataDummy
import com.dicoding.film.vo.Resource
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
    private lateinit var filmObserver: Observer<Resource<FilmEntity>>

    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel(filmRepository)

        viewModel.setSelectedFilm(filmId)
        viewModelTv = DetailFilmViewModel(filmRepository)
        viewModelTv.setSelectedFilm(tvId)
    }

    @Test
    fun getFilm() {
        val dummyDetailFilm = Resource.success(dummyFilm)
        val film = MutableLiveData<Resource<FilmEntity>>()
        film.value = dummyDetailFilm
        `when`(filmRepository.getDetailFilm(filmId)).thenReturn(film)
        viewModel.getFilm.observeForever(filmObserver)
        verify(filmObserver).onChanged(dummyDetailFilm)
    }

    @Test
    fun getTvShow() {
        val dummyDetailTv = Resource.success(dummyTvShow)
        val tv = MutableLiveData<Resource<FilmEntity>>()
        tv.value = dummyDetailTv
        `when`(filmRepository.getDetailTv(tvId)).thenReturn(tv)
        viewModel.getTvShow.observeForever(filmObserver)
        verify(filmObserver).onChanged(dummyDetailTv)
    }

    @Test
    fun setFavoriteFilm() {
        val expected = MutableLiveData<Resource<FilmEntity>>()
        expected.value = Resource.success(DataDummy.generateDummyFilm()[0])
        `when`(filmRepository.getDetailFilm(filmId)).thenReturn(expected)
        viewModel.setFavoriteFilm()
        viewModel.getFilm.observeForever(filmObserver)
        verify(filmObserver).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModel.getFilm.value
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun setFavoriteTv() {
        val expected = MutableLiveData<Resource<FilmEntity>>()
        expected.value = Resource.success(DataDummy.generateDummyTvShows()[0])
        `when`(filmRepository.getDetailTv(tvId)).thenReturn(expected)
        viewModel.setFavoriteTv()
        viewModel.getTvShow.observeForever(filmObserver)
        verify(filmObserver).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModel.getTvShow.value
        assertEquals(expectedValue, actualValue)
    }


}