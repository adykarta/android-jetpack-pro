package com.dicoding.film.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.film.R
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.databinding.ActivityDetailFilmBinding
import com.dicoding.film.databinding.ContentDetailFilmBinding
import com.dicoding.film.ui.viewmodel.ViewModelFactory
import com.dicoding.film.vo.Status

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
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailFilmViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val id= extras.getInt(EXTRA_FILM)
            val type = extras.getString(EXTRA_TYPE)
            if (id != 0) {
                viewModel.setSelectedFilm(id)
                if(type=="film"){
                    viewModel.getFilm.observe(this, Observer{ film ->
                        if(film!=null){
                            when(film.status){
                                Status.LOADING ->  detailContentBinding.progressBar.visibility = View.VISIBLE
                                Status.SUCCESS -> if (film.data != null) {
                                    detailContentBinding.progressBar.visibility = View.GONE
                                    populateFilm(film.data)
                                }
                                Status.ERROR ->   {
                                    detailContentBinding.progressBar.visibility = View.GONE
                                    Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                                }

                            }

                        }


                    })
                }
                else{
                    viewModel.getTvShow.observe(this, Observer{ film ->
                        if(film!=null){
                            when(film.status){
                                Status.LOADING ->  detailContentBinding.progressBar.visibility = View.VISIBLE
                                Status.SUCCESS -> if (film.data != null) {
                                    detailContentBinding.progressBar.visibility = View.GONE
                                    populateFilm(film.data)
                                }
                                Status.ERROR ->   {
                                    detailContentBinding.progressBar.visibility = View.GONE
                                    Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                                }

                            }

                        }

                    })

                }

            }
        }
    }
    private fun populateFilm(filmEntity: FilmEntity) {

        val genre = StringBuilder()
        for(i in filmEntity.genre){
            genre.append(i.name.toString()).append(" ")
        }

        detailContentBinding.apply {
            textTitle.text = filmEntity.title
            textDescription.text = filmEntity.overview
            textGenrefilm.text = genre.toString()
            (filmEntity.userScore.toString()+"/10").also { textRating.text = it }
            textDuration.text = if (filmEntity.duration==0) "-"  else filmEntity.duration.toString()+"m"
            textDate.text = filmEntity.releaseYear

            Glide.with(this@DetailFilmActivity)
                .load(filmEntity.photo)
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(imagePoster)
        }
    }
}