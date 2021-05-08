package com.dicoding.film.ui.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.repository.FilmRepository
import com.dicoding.film.vo.Resource


class FilmViewModel(private val filmRepository: FilmRepository): ViewModel() {
    fun getFilm(): LiveData<Resource<PagedList<FilmEntity>>> = filmRepository.getAllFilm()
}