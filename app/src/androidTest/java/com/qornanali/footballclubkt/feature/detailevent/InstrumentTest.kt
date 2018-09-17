package com.qornanali.footballclubkt.feature.detailevent

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.qornanali.footballclub_kotlin.R.id.*
import com.qornanali.footballclubkt.feature.home.HomeActivity
import com.qornanali.footballclubkt.feature.listleague.DisplayListLeagueActivity
import kotlinx.android.synthetic.main.activity_home.view.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class InstrumentTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(DisplayListLeagueActivity::class.java)

    @Test
    fun updateMyFavorite() {
        Thread.sleep(5000)
        onView(withId(rv_leagues)).check(matches(isDisplayed()))
        onView(withId(rv_leagues)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(40))
        onView(withId(rv_leagues)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(40, click()))
        onView(withId(view_pager)).check(matches(isDisplayed()))
        onView(withId(view_pager)).perform(swipeLeft())
        Thread.sleep(5000)
        onView(withId(rv_last_events)).check(matches(isDisplayed()))
        onView(withId(rv_last_events)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(rv_last_events)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        onView(withId(m_action_favorites)).check(matches(isDisplayed()))
        onView(withId(m_action_favorites)).perform(click())
        pressBack()
        onView(withId(m_action_favorites)).check(matches(isDisplayed()))
        onView(withId(m_action_favorites)).perform(click())
    }

    @Test
    fun updateFavoriteTeamViaSearch(){
//        Thread.sleep(5000)
//        onView(withId(rv_leagues)).check(matches(isDisplayed()))
//        onView(withId(rv_leagues)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(40))
//        onView(withId(rv_leagues)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(40, click()))
//        onView(withId(view_pager)).check(matches(isDisplayed()))
//        onView(withId(view_pager)).perform(swipeLeft())
//        onView(withId(view_pager)).perform(swipeLeft())
//        onView(withId(m_action_search)).check(matches(isDisplayed()))
//        onView(withId(m_action_search)).perform(click())
//        onView(withId(et_query)).check(matches(isDisplayed()))
//        onView(withId(et_query)).perform(typeText("Arsenal"))
//        Thread.sleep(5000)
//        onView(withId(rv_teams)).check(matches(isDisplayed()))
//        onView(withId(rv_teams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
//        onView(withId(rv_teams)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onView(withId(m_action_favorites)).check(matches(isDisplayed()))
//        onView(withId(m_action_favorites)).perform(click())
//        pressBack()
//        onView(withId(m_action_favorites)).check(matches(isDisplayed()))
//        onView(withId(m_action_favorites)).perform(click())
    }
}