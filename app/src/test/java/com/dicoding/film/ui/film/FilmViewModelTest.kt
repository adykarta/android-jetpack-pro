package com.dicoding.film.ui.film

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FilmViewModelTest{
    private lateinit var viewModel: FilmViewModel
    @Before
    fun setUp() {
        viewModel = FilmViewModel()
    }

    @Test
    fun getFilm() {
        val filmEntities = viewModel.getFilm()
        assertNotNull(filmEntities)
        assertEquals(10, filmEntities.size)
    }

}
