package com.dicoding.film.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.film.R
import com.dicoding.film.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{
    private val dummyFilm = DataDummy.generateDummyFilm()
    private val dummyTvShow = DataDummy.generateDummyTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadFilms() {
        onView(withText("Movies")).perform(click())
        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyFilm.size-1))
    }

    @Test
    fun loadDetailFilm() {
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyFilm[0].title)))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(withText(dummyFilm[0].releaseYear.toString())))
        onView(withId(R.id.text_genrefilm)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genrefilm)).check(matches(withText(dummyFilm[0].genre)))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_description)).check(matches(withText(dummyFilm[0].overview)))
        onView(withId(R.id.text_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.text_rating)).check(matches(withText(dummyFilm[0].userScore.toString()+"%")))
        onView(withId(R.id.text_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_duration)).check(matches(withText(dummyFilm[0].duration.toString()+"m")))
    }


    @Test
    fun loadTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size-1))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyTvShow[0].title)))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(withText(dummyTvShow[0].releaseYear.toString())))
        onView(withId(R.id.text_genrefilm)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genrefilm)).check(matches(withText(dummyTvShow[0].genre)))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_description)).check(matches(withText(dummyTvShow[0].overview)))
        onView(withId(R.id.text_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.text_rating)).check(matches(withText(dummyTvShow[0].userScore.toString()+"%")))
        onView(withId(R.id.text_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_duration)).check(matches(withText(dummyTvShow[0].duration.toString()+"m")))
    }
}