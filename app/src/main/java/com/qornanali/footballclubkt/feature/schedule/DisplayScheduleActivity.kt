package com.qornanali.footballclubkt.feature.schedule

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.SchedulePagerAdapter
import com.qornanali.footballclubkt.feature.BaseActivity
import com.qornanali.footballclubkt.feature.favoritevent.DisplayFavoriteEventsActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


class DisplayScheduleActivity : BaseActivity<DisplayScheduleAPresenter, DisplayScheduleAView>(), DisplayScheduleAView {

    override fun attachPresenter(): DisplayScheduleAPresenter {
        return DisplayScheduleAPresenter()
    }

    private lateinit var pagerAdapter: SchedulePagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout

    override fun displayTabs(fragments: List<Fragment>, titles: List<String>) {
        for (i in 0..fragments.size - 1) {
            pagerAdapter.addFragment(fragment = fragments.get(i), title = titles.get(i))
        }
        pagerAdapter.notifyDataSetChanged()
    }

    override fun displayActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun attachLayout(): Int {
        return R.layout.activity_displayschedule
    }

    override fun onInitializeViews() {
        toolbar = find(R.id.toolbar)
        viewPager = find(R.id.view_pager)
        tabLayout = find(R.id.tab_layout)
        pagerAdapter = SchedulePagerAdapter(supportFragmentManager)
        setSupportActionBar(toolbar)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        presenter.attachView(this)

        presenter.setActionBar(resources)

        presenter.setTabs(resources)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorites, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.m_action_favorites -> startActivity<DisplayFavoriteEventsActivity>()
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }
}