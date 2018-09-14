package com.qornanali.footballclubkt.feature.detailevent

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.qornanali.footballclub_kotlin.R.id.m_action_favorites
import com.qornanali.footballclub_kotlin.R.id.rv_events_2
import com.qornanali.footballclubkt.feature.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class InstrumentTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun updateMyFavorite() {
        Thread.sleep(5000)
        onView(withId(rv_events_2)).check(matches(isDisplayed()))
        onView(withId(rv_events_2)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(rv_events_2)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        onView(withId(m_action_favorites)).check(matches(isDisplayed()))
        onView(withId(m_action_favorites)).perform(click())
        pressBack()
        onView(withId(m_action_favorites)).check(matches(isDisplayed()))
        onView(withId(m_action_favorites)).perform(click())
    }
}