package com.dicoding.film.ui.detail

import com.dicoding.film.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailFilmViewModelTest{
    private lateinit var viewModel: DetailFilmViewModel
    private val dummyFilm = DataDummy.generateDummyFilm()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]


    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel()
    }

    @Test
    fun getFilm() {
        viewModel.setSelectedFilm(dummyFilm.title)
        val filmEntity = viewModel.getFilm()
        assertNotNull(filmEntity)
        assertEquals(dummyFilm.title, filmEntity.title)
        assertEquals(dummyFilm.releaseYear, filmEntity.releaseYear)
        assertEquals(dummyFilm.overview, filmEntity.overview)
        assertEquals(dummyFilm.photo, filmEntity.photo)
        assertEquals(dummyFilm.duration, filmEntity.duration)
        assertEquals(dummyFilm.userScore, filmEntity.userScore)
        assertEquals(dummyFilm.genre, filmEntity.genre)
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedFilm(dummyTvShow.title)
        val tvShowEntity = viewModel.getTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.title,tvShowEntity.title)
        assertEquals(dummyTvShow.releaseYear, tvShowEntity.releaseYear)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.photo, tvShowEntity.photo)
        assertEquals(dummyTvShow.duration, tvShowEntity.duration)
        assertEquals(dummyTvShow.userScore, tvShowEntity.userScore)
        assertEquals(dummyTvShow.genre, tvShowEntity.genre)
    }

}