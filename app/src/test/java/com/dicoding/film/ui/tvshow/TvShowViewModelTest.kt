package com.dicoding.film.ui.tvshow

import com.dicoding.film.ui.film.FilmViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest{
    private lateinit var viewModel: TvShowViewModel
    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow() {
        val tvshowEntities = viewModel.getTvShow()
        assertNotNull(tvshowEntities)
        assertEquals(10, tvshowEntities.size)
    }
}
