package com.dicoding.film.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.film.R
import com.dicoding.film.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matcher
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

    @Test
    fun loadFavoriteFilm(){
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.fav)).perform(click())
        onView(withId(R.id.rv_film_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_film_favorite)).check(RecyclerViewItemCountAssertion(1));
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(pressBack())
    }

    @Test
    fun loadFavoriteTv(){
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.fav)).perform(click())
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tvshow_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow_favorite)).check(RecyclerViewItemCountAssertion(1))
        onView(isRoot()).perform(pressBack())
        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(pressBack())
    }



}

class RecyclerViewItemCountAssertion : ViewAssertion {
    private val matcher: Matcher<Int?>?

    constructor(expectedCount: Int) {
        matcher = `is`(expectedCount)
    }

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView = view as RecyclerView?
        val adapter = recyclerView!!.adapter
        assertThat(adapter!!.itemCount, matcher)
    }
}