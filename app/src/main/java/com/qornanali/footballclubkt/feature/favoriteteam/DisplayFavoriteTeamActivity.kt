package com.qornanali.footballclubkt.feature.favoriteteam

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.ListTeamAdapter
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.database
import com.qornanali.footballclubkt.feature.BaseActivity
import com.qornanali.footballclubkt.feature.detailteam.DisplayDetailTeamActivity
import com.qornanali.footballclubkt.model.FavoriteTeam
import com.qornanali.footballclubkt.model.Team
import com.qornanali.footballclubkt.util.OnItemClickListener
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class DisplayFavoriteTeamActivity :
        BaseActivity<DisplayFavoriteTeamAPresenter, DisplayFavoriteTeamAView>(),
        DisplayFavoriteTeamAView {

    private lateinit var toolbar: Toolbar
    private lateinit var rvTeams: RecyclerView
    private lateinit var adapter: ListTeamAdapter
    private val teams = ArrayList<Team>()

    override fun onInitializeViews() {
        toolbar = find(R.id.toolbar)
        rvTeams = find(R.id.rv_teams)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = ListTeamAdapter(teams, OnItemClickListener {
            startActivity<DisplayDetailTeamActivity>("team" to it)
        })

        rvTeams.layoutManager = GridLayoutManager(this, 2)
        rvTeams.adapter = adapter

        presenter.setActionBar(resources)

        presenter.loadFavoriteTeam(database)
    }

    override fun attachPresenter(): DisplayFavoriteTeamAPresenter {
        return DisplayFavoriteTeamAPresenter(Gson(), ApiRepository(), this)
    }


    override fun showError(message: CharSequence?) {
        if (message != null) {
            toast(message)
        }
    }

    override fun showFavoriteTeams(data: List<FavoriteTeam>) {
        for (favorite in data) {
            teams.add(Team(favorite))
        }
        adapter.notifyDataSetChanged()
    }

    override fun displayActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun attachLayout(): Int {
        return R.layout.activity_displayfavoriteteams
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
}
