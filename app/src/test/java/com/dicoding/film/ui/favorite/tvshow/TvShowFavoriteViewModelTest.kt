package com.dicoding.film.ui.favorite.tvshow

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
class TvShowFavoriteViewModelTest {

    private lateinit var viewModel: TvShowFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var pagedList: PagedList<FilmEntity>

    @Mock
    private lateinit var observer: Observer<PagedList<FilmEntity>>
    @Before
    fun setUp() {
        viewModel = TvShowFavoriteViewModel(filmRepository)
    }

    @Test
    fun getTv() {
        val dummyTvs = pagedList
        Mockito.`when`(dummyTvs.size).thenReturn(1)
        val films = MutableLiveData<PagedList<FilmEntity>>()
        films.value = dummyTvs
        Mockito.`when`(filmRepository.getFavoritedTvShow()).thenReturn(films)
        val filmEntities = viewModel.getTvShowFavorite().value
        Mockito.verify<FilmRepository>(filmRepository).getFavoritedTvShow()
        assertNotNull(filmEntities)
        assertEquals(1, filmEntities?.size)
        viewModel.getTvShowFavorite().observeForever(observer)
        Mockito.verify(observer).onChanged(filmEntities)

    }
}