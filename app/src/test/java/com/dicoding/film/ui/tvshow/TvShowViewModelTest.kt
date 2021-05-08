package com.dicoding.film.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.repository.FilmRepository
import com.dicoding.film.vo.Resource
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
    private lateinit var pagedList: PagedList<FilmEntity>

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<FilmEntity>>>
    @Before
    fun setUp() {
        viewModel = TvShowViewModel(filmRepository)
    }

    @Test
    fun getTv() {
        val dummyTvs = Resource.success(pagedList)
        Mockito.`when`(dummyTvs.data?.size).thenReturn(1)
        val films = MutableLiveData<Resource<PagedList<FilmEntity>>>()
        films.value = dummyTvs
        Mockito.`when`(filmRepository.getAllTvShow()).thenReturn(films)
        val filmEntities = viewModel.getTvShow().value
        Mockito.verify<FilmRepository>(filmRepository).getAllTvShow()
        assertNotNull(filmEntities)
        assertEquals(1, filmEntities?.data?.size)
        viewModel.getTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(filmEntities)

    }
}