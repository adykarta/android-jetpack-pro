package com.dicoding.film.ui.tvshow

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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<List<FilmEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(filmRepository)
    }

    @Test
    fun getTv() {
        val dummyFilms = DataDummy.generateDummyTvShows()
        val films = MutableLiveData<List<FilmEntity>>()
        films.value = dummyFilms
        Mockito.`when`(filmRepository.getAllTvShow()).thenReturn(films)
        val filmEntities = viewModel.getTvShow().value
        Mockito.verify<FilmRepository>(filmRepository).getAllTvShow()
        assertNotNull(filmEntities)
        assertEquals(1, filmEntities?.size)
        viewModel.getTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(filmEntities)

    }
}