package com.qornanali.footballclubkt.feature.detailteam

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.PagerAdapter
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.database
import com.qornanali.footballclubkt.feature.BaseActivity
import com.qornanali.footballclubkt.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class DetailTeamActivity :
        BaseActivity<DetailTeamAPresenter, DetailTeamAView>(),
        DetailTeamAView {

    private lateinit var viewPager: ViewPager
    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var pagerAdapter: PagerAdapter
    var team: Team? = null

    override fun attachLayout(): Int {
        return R.layout.activity_detailteam
    }

    override fun onInitializeViews() {
        team = intent.extras.getSerializable("team") as Team

        toolbar = find(R.id.toolbar)
        viewPager = find(R.id.view_pager)
        tabLayout = find(R.id.tab_layout)
        pagerAdapter = PagerAdapter(supportFragmentManager)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        presenter.loadTeamInfo(team)
        presenter.setTabs(resources)
    }

    override fun attachPresenter(): DetailTeamAPresenter {
        return DetailTeamAPresenter(Gson(), ApiRepository(), this)
    }

    override fun displayActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_event, menu)
//        favoriteMenu = menu?.getItem(0)
//        if (checkFavorited()) {
//            setMenuItemIcon(favoriteMenu, R.drawable.ic_action_star_2)
//        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.m_action_favorites -> {

            }
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
}
