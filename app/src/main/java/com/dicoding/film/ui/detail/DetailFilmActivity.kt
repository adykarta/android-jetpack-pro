package com.dicoding.film.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.film.R
import com.dicoding.film.data.FilmEntity
import com.dicoding.film.databinding.ActivityDetailFilmBinding
import com.dicoding.film.databinding.ContentDetailFilmBinding

class DetailFilmActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var detailContentBinding: ContentDetailFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailFilmBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailFilmBinding.detailContent

        setContentView(activityDetailFilmBinding.root)

        setSupportActionBar(activityDetailFilmBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailFilmViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val id= extras.getInt(EXTRA_FILM)
            val type = extras.getString(EXTRA_TYPE)
            if (id != 0) {
                viewModel.setSelectedFilm(id)
                if(type=="film"){
                    detailContentBinding.progressBar.visibility = View.VISIBLE
                    viewModel.getFilm().observe(this, Observer{ film ->
                       detailContentBinding.progressBar.visibility = View.GONE
                        populateFilm(film)

                    })



                }
                else{
                    detailContentBinding.progressBar.visibility = View.VISIBLE
                    viewModel.getTvShow().observe(this, Observer{ tv ->
                        detailContentBinding.progressBar.visibility = View.GONE
                        populateFilm(tv)

                    })

                }

            }
        }
    }
    private fun populateFilm(filmEntity: FilmEntity) {
        detailContentBinding.textTitle.text = filmEntity.title
        detailContentBinding.textDescription.text = filmEntity.overview
        detailContentBinding.textGenrefilm.text = filmEntity.genre
        detailContentBinding.textRating.text = filmEntity.userScore.toString()+"%"
        detailContentBinding.textDuration.text = filmEntity.duration.toString()+"m"
        detailContentBinding.textDate.text = filmEntity.releaseYear.toString()

        Glide.with(this)
            .load(filmEntity.photo)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentBinding.imagePoster)

    }
}