package com.dicoding.film.ui.favorite.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.film.databinding.ActivityHomeFavoriteBinding



class HomeFavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityHomeFavoriteBinding = ActivityHomeFavoriteBinding.inflate(layoutInflater)
        setContentView(activityHomeFavoriteBinding.root)
        val sectionsPagerAdapter = SectionsPagerFavoriteAdapter(this, supportFragmentManager)
        activityHomeFavoriteBinding.viewPagerFavorite.adapter = sectionsPagerAdapter
        activityHomeFavoriteBinding.tabsFavorite.setupWithViewPager(activityHomeFavoriteBinding.viewPagerFavorite)
        supportActionBar?.elevation = 0f
        supportActionBar?.title = "Favorite List"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}