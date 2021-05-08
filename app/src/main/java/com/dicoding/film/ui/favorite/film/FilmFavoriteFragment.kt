package com.dicoding.film.ui.favorite.film

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.film.R
import com.dicoding.film.databinding.FragmentFilmBinding
import com.dicoding.film.databinding.FragmentFilmFavoriteBinding
import com.dicoding.film.ui.film.FilmAdapter
import com.dicoding.film.ui.film.FilmViewModel
import com.dicoding.film.ui.viewmodel.ViewModelFactory
import com.dicoding.film.vo.Status
import com.google.android.material.snackbar.Snackbar

class FilmFavoriteFragment : Fragment() {
    private lateinit var fragmentFilmFavoriteBinding: FragmentFilmFavoriteBinding
    private lateinit var viewModel: FilmFavoriteViewModel
    private lateinit var filmAdapter: FilmFavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentFilmFavoriteBinding = FragmentFilmFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentFilmFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentFilmFavoriteBinding.rvFilmFavorite)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FilmFavoriteViewModel::class.java]
            filmAdapter = FilmFavoriteAdapter()
            fragmentFilmFavoriteBinding.progressBarFavorite.visibility = View.VISIBLE
            viewModel.getFavoriteFilm().observe(this, Observer{ films ->
                if(films !=null){
                    fragmentFilmFavoriteBinding.progressBarFavorite.visibility = View.GONE
                    filmAdapter.submitList(films)
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
    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val courseEntity = filmAdapter.getSwipedData(swipedPosition)
                courseEntity?.let { viewModel.setFavorite(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    courseEntity?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }
    })
}