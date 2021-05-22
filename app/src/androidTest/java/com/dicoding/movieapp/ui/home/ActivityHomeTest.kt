package com.dicoding.movieapp.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dicoding.movieapp.R
import com.dicoding.movieapp.utils.DataDummy
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ActivityHomeTest {

    private val dummyMovie = DataDummy.createDataMovieDummy()
    private val dummyTvShow = DataDummy.createDataTvShowDummy()

    @get:Rule
    var activityRule = ActivityScenarioRule(ActivityHome::class.java)

    @Test
    fun loadMovieTest(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun detailMovieTest(){
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_detail_movie)).check(matches(withText(dummyMovie[0].name)))
        onView(withId(R.id.tv_desc_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc_detail_movie)).check(matches(withText(dummyMovie[0].desc)))
        onView(withId(R.id.iv_detail_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShowTest(){
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun detailTvShowTest(){
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_detail_movie)).check(matches(withText(dummyTvShow[0].name)))
        onView(withId(R.id.tv_desc_detail_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc_detail_movie)).check(matches(withText(dummyTvShow[0].desc)))
        onView(withId(R.id.iv_detail_movie)).check(matches(isDisplayed()))
    }

}