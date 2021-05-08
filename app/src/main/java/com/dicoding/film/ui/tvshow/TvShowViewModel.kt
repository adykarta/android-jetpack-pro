package com.dicoding.film.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.repository.FilmRepository
import com.dicoding.film.vo.Resource


class TvShowViewModel(private val filmRepository: FilmRepository): ViewModel() {
    fun getTvShow(): LiveData<Resource<PagedList<FilmEntity>>> = filmRepository.getAllTvShow()
}