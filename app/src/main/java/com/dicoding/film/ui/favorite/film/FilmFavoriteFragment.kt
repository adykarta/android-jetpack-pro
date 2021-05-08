package com.dicoding.film.ui.favorite.film

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.film.R
import com.dicoding.film.databinding.FragmentFilmBinding
import com.dicoding.film.databinding.FragmentFilmFavoriteBinding
import com.dicoding.film.ui.film.FilmAdapter
import com.dicoding.film.ui.film.FilmViewModel
import com.dicoding.film.ui.viewmodel.ViewModelFactory
import com.dicoding.film.vo.Status

class FilmFavoriteFragment : Fragment() {
    private lateinit var fragmentFilmFavoriteBinding: FragmentFilmFavoriteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentFilmFavoriteBinding = FragmentFilmFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentFilmFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FilmFavoriteViewModel::class.java]
            val filmAdapter = FilmFavoriteAdapter()
            fragmentFilmFavoriteBinding.progressBarFavorite.visibility = View.VISIBLE
            viewModel.getFavoriteFilm().observe(this, Observer{ films ->
                if(films !=null){
                    fragmentFilmFavoriteBinding.progressBarFavorite.visibility = View.GONE
                    filmAdapter.setFilm(films)
                    filmAdapter.notifyDataSetChanged()
                }

            })

            with(fragmentFilmFavoriteBinding.rvFilmFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = filmAdapter
            }
        }
    }
}