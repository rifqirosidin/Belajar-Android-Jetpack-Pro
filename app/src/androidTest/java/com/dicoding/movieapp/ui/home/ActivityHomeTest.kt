package com.dicoding.movieapp.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.movieapp.R
import com.dicoding.movieapp.utils.DataDummy
import com.dicoding.movieapp.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ActivityHomeTest {

    private val dummyMovie = DataDummy.getDummyMovies()
    private val dummyTvShow = DataDummy.getDummyTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(ActivityHome::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovieTest() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun detailMovieTest() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    5,
                    click()
                )
            )

        onView(withId(R.id.iv_detail_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_detail_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc_detail_movie))
            .check(matches(isDisplayed()))

        pressBack()
    }

    @Test
    fun loadTvShowTest(){
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun detailTvShowTest(){
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.rv_tv_show))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    5,
                    click()
                )
            )

        onView(withId(R.id.iv_detail_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_detail_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc_detail_movie))
            .check(matches(isDisplayed()))

        pressBack()
    }

    @Test
    fun loadFavoriteMovies(){
        onView(withId(R.id.fap_favorite_movie)).perform(click())
        onView(withId(R.id.rv_favorite_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadDetailFavoriteMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab_add_favorite)).perform(click())
        pressBack()
        onView(withId(R.id.fap_favorite_movie)).perform(click())
        onView(withId(R.id.rv_favorite_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.iv_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add_favorite)).perform(click())
        onView(withId(R.id.tv_title_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc_detail_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteTvShows(){
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.fap_favorite_tv_show)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_favorite_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadDetailFavoriteTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.fab_add_favorite)).perform(click())
        pressBack()
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.fap_favorite_tv_show)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_favorite_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.iv_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add_favorite)).perform(click())
        onView(withId(R.id.tv_title_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc_detail_movie)).check(matches(isDisplayed()))
    }

}