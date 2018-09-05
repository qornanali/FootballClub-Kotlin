package com.qornanali.footballclubkt.feature.detailevent

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.feature.schedule.DisplayScheduleActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DisplayScheduleActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(DisplayScheduleActivity::class.java)

    @Test
    fun openMyFavoriteEvent() {
        onView(withId(R.id.m_action_favorites))
                .check(matches(isDisplayed()))
        onView(withId(R.id.m_action_favorites)).perform(click())
        onView(withId(R.id.rv_events))
                .check(matches(isDisplayed()))
        onView(withId(R.id.rv_events)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(R.id.rv_events)).perform(
              RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
    }
}