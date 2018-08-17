package com.qornanali.footballclubkt.feature.leagueschedule

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.ViewPagerAdapter


class DisplayEventsActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displayevents)

        toolbar = findViewById(R.id.toolbar)
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)
        pagerAdapter = ViewPagerAdapter(supportFragmentManager)

        setSupportActionBar(toolbar)

        supportActionBar?.title = resources.getString(R.string.league_name)

        pagerAdapter.addFragment(DisplayEventsFragment(), getString(R.string.next_events))
        pagerAdapter.addFragment(DisplayEventsFragment(), getString(R.string.last_events))

        viewPager.adapter = pagerAdapter

        tabLayout.setupWithViewPager(viewPager)
    }
}
