package com.qornanali.footballclubkt.feature.home

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.SchedulePagerAdapter
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.feature.BaseActivity
import com.qornanali.footballclubkt.model.League
import org.jetbrains.anko.find


class HomeActivity :
        BaseActivity<HomeAPresenter, HomeAView>(),
        HomeAView {

    private lateinit var pagerAdapter: SchedulePagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    var league: League? = null

    override fun attachPresenter(): HomeAPresenter {
        return HomeAPresenter(Gson(), ApiRepository(), this)
    }

    override fun attachLayout(): Int {
        return R.layout.activity_home
    }

    override fun onInitializeViews() {
        league = intent.extras.getSerializable("league") as League

        toolbar = find(R.id.toolbar)
        viewPager = find(R.id.view_pager)
        tabLayout = find(R.id.tab_layout)
        pagerAdapter = SchedulePagerAdapter(supportFragmentManager)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        presenter.setActionBar(league?.strLeague ?: resources.getString(R.string.app_name))

        presenter.setTabs(resources)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }


    override fun displayTabs(fragments: List<Fragment>, titles: List<String>) {
        for (i in 0..fragments.size - 1) {
            pagerAdapter.addFragment(fragment = fragments.get(i), title = titles.get(i))
        }
        pagerAdapter.notifyDataSetChanged()
    }

    override fun displayActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

}
