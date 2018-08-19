package com.qornanali.footballclubkt.feature

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.util.adapter.SchedulePagerAdapter
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


class DisplayScheduleActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: SchedulePagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displayschedule)

        toolbar = find(R.id.toolbar)
        viewPager = find(R.id.view_pager)
        tabLayout = find(R.id.tab_layout)
        pagerAdapter = SchedulePagerAdapter(supportFragmentManager)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.league_name)

        pagerAdapter.addFragment(DisplayListEventsFragment(), getString(R.string.next_events))
        pagerAdapter.addFragment(DisplayListEventsFragment(), getString(R.string.last_events))

        viewPager.adapter = pagerAdapter

        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorites, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.m_favorites -> startActivity<DisplayFavoriteEventsActivity>()
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }
}
