package com.dicoding.film.ui.film
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
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FilmViewModelTest {

    private lateinit var viewModel: FilmViewModel

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
        viewModel = FilmViewModel(filmRepository)
    }

    @Test
    fun getFilm() {
        val dummyFilms = Resource.success(pagedList)
        `when`(dummyFilms.data?.size).thenReturn(1)
        val films = MutableLiveData<Resource<PagedList<FilmEntity>>>()
        films.value = dummyFilms
        `when`(filmRepository.getAllFilm()).thenReturn(films)
        val filmEntities = viewModel.getFilm().value
        verify<FilmRepository>(filmRepository).getAllFilm()
        assertNotNull(filmEntities)
        assertEquals(1, filmEntities?.data?.size)
        viewModel.getFilm().observeForever(observer)
        verify(observer).onChanged(filmEntities)


    }
}