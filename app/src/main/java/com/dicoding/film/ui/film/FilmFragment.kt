package com.dicoding.film.ui.film

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.film.databinding.FragmentFilmBinding
import com.dicoding.film.viewmodel.ViewModelFactory


class FilmFragment : Fragment() {
    private lateinit var fragmentFilmBinding: FragmentFilmBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentFilmBinding = FragmentFilmBinding.inflate(layoutInflater, container, false)
        return fragmentFilmBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FilmViewModel::class.java]
            val filmAdapter = FilmAdapter()
            fragmentFilmBinding.progressBar.visibility = View.VISIBLE


            viewModel.getFilm().observe(this, Observer{ films ->
                fragmentFilmBinding.progressBar.visibility = View.GONE
                filmAdapter.setFilm(films)
                filmAdapter.notifyDataSetChanged()
            })

            with(fragmentFilmBinding.rvFilm) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = filmAdapter
            }
        }
    }
}