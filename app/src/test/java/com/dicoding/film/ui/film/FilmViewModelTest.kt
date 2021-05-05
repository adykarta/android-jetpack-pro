package com.dicoding.film.ui.film
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
class FilmViewModelTest {

    private lateinit var viewModel: FilmViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<List<FilmEntity>>

    @Before
    fun setUp() {
        viewModel = FilmViewModel(filmRepository)
    }

    @Test
    fun getFilm() {
        val dummyFilms = DataDummy.generateDummyFilm()
        val films = MutableLiveData<List<FilmEntity>>()
        films.value = dummyFilms
        `when`(filmRepository.getAllFilm()).thenReturn(films)
        val filmEntities = viewModel.getFilm().value
        verify<FilmRepository>(filmRepository).getAllFilm()
        assertNotNull(filmEntities)
        assertEquals(1, filmEntities?.size)
        viewModel.getFilm().observeForever(observer)
        verify(observer).onChanged(filmEntities)

    }
}