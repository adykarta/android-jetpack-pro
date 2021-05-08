package com.dicoding.film.ui.favorite.tvshow

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
import com.dicoding.film.databinding.FragmentTvShowBinding
import com.dicoding.film.databinding.FragmentTvShowFavoriteBinding
import com.dicoding.film.ui.tvshow.TvShowAdapter
import com.dicoding.film.ui.tvshow.TvShowViewModel
import com.dicoding.film.ui.viewmodel.ViewModelFactory
import com.dicoding.film.vo.Status

class TvShowFavoriteFragment : Fragment() {
    private lateinit var fragmentTvShowFavoriteBinding: FragmentTvShowFavoriteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowFavoriteBinding = FragmentTvShowFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowFavoriteViewModel::class.java]

            val tvShowAdapter = TvShowFavoriteAdapter()
            fragmentTvShowFavoriteBinding.progressBarFavorite.visibility = View.VISIBLE
            viewModel.getTvShowFavorite().observe(this, Observer{ films ->
                if(films !=null){
                    fragmentTvShowFavoriteBinding.progressBarFavorite.visibility = View.GONE
                    tvShowAdapter.setTvShow(films)
                    tvShowAdapter.notifyDataSetChanged()
                }

            })

            with(fragmentTvShowFavoriteBinding.rvTvshowFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}