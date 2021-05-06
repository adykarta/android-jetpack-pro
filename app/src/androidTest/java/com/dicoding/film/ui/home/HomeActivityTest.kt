package com.dicoding.film.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.film.R
import com.dicoding.film.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test



class HomeActivityTest{

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadFilms() {
        onView(withText("Movies")).perform(click())
        onView(withId(R.id.rv_film)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
    }

    @Test
    fun loadDetailFilm() {
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genrefilm)).check(matches(isDisplayed()))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.text_duration)).check(matches(isDisplayed()))

    }

    @Test
    fun loadTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genrefilm)).check(matches(isDisplayed()))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.text_duration)).check(matches(isDisplayed()))


    }
}