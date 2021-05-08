package com.dicoding.film.ui.favorite.film
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
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
class FilmFavoriteViewModelTest {

    private lateinit var viewModel: FilmFavoriteViewModel

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
        viewModel = FilmFavoriteViewModel(filmRepository)
    }

    @Test
    fun getFilm() {
        val dummyFilms = pagedList
        `when`(dummyFilms.size).thenReturn(1)
        val films = MutableLiveData<PagedList<FilmEntity>>()
        films.value =dummyFilms
        `when`(filmRepository.getFavoritedFilm()).thenReturn(films)
        val filmEntities = viewModel.getFavoriteFilm().value
        verify<FilmRepository>(filmRepository).getFavoritedFilm()
        assertNotNull(filmEntities)
        assertEquals(1, filmEntities?.size)
        viewModel.getFavoriteFilm().observeForever(observer)
        verify(observer).onChanged(filmEntities)


    }
}