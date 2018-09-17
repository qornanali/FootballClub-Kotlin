package com.qornanali.footballclubkt.feature.detailteam

import android.database.sqlite.SQLiteConstraintException
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.PagerAdapter
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.database
import com.qornanali.footballclubkt.feature.BaseActivity
import com.qornanali.footballclubkt.model.FavoriteTeam
import com.qornanali.footballclubkt.model.Team
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class DisplayDetailTeamActivity :
        BaseActivity<DisplayDetailTeamAPresenter, DisplayDetailTeamAView>(),
        DisplayDetailTeamAView {

    private lateinit var viewPager: ViewPager
    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var pagerAdapter: PagerAdapter
    private var favoriteMenu: MenuItem? = null
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

    override fun attachPresenter(): DisplayDetailTeamAPresenter {
        return DisplayDetailTeamAPresenter(Gson(), ApiRepository(), this)
    }

    override fun displayActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_event, menu)
        favoriteMenu = menu?.getItem(0)
        if (checkFavorited()) {
            setMenuItemIcon(favoriteMenu, R.drawable.ic_action_star_2)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.m_action_favorites -> {
                val statusFavorited = checkFavorited()
                if (statusFavorited) {
                    presenter.removeFromFavorite(team?.idTeam, database)
                } else {
                    presenter.addToFavorite(team, database)
                }
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


    private fun setMenuItemIcon(menuItem: MenuItem?, drawable: Int) {
        menuItem?.icon = ContextCompat.getDrawable(this, drawable)
    }

    override fun showError(message: CharSequence?) {
        if (message != null) {
            toast(message)
        }
    }


    override fun successRemovedFromFavorite() {
        toast(resources.getString(R.string.removed_from_favorites))
        setMenuItemIcon(favoriteMenu, R.drawable.ic_action_star)
    }

    override fun successAddedToFavorite() {
        toast(resources.getString(R.string.added_to_favorites))
        setMenuItemIcon(favoriteMenu, R.drawable.ic_action_star_2)
    }


    private fun checkFavorited(): Boolean {
        var favorited = false
        try {
            database.use {
                val q = select(FavoriteTeam.TABLE_FAVORITETEAM, FavoriteTeam.FIELD_IDTEAM)
                        .whereArgs("(FIELD_IDTEAM = {value})",
                                "value" to (team?.idTeam ?: ""))
                val listFavorites = q.parseList(classParser<String>())
                favorited = !listFavorites.isEmpty()
            }
        } catch (e: SQLiteConstraintException) {
            showError(e.message)
        }
        return favorited
    }
}
