package com.dicoding.film.ui.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.film.data.FilmEntity
import com.dicoding.film.utils.DataDummy

class TvShowViewModel: ViewModel() {
    fun getTvShow(): List<FilmEntity> = DataDummy.generateDummyTvShows()
}