package com.qornanali.footballclubkt.feature.listteam

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.ListTeamAdapter
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.feature.BaseFragment
import com.qornanali.footballclubkt.model.Team
import org.jetbrains.anko.find

class DisplayListTeamFragment :
        BaseFragment<DisplayListTeamFPresenter, DisplayListTeamFView>(),
        DisplayListTeamFView {

    private lateinit var rvEvents: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: ListTeamAdapter
    private val teams = ArrayList<Team>()

    override fun attachPresenter(): DisplayListTeamFPresenter {
        return DisplayListTeamFPresenter(Gson(), ApiRepository(), this)
    }

    override fun showListTeam(data: List<Team>) {
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onInitializeViews() {
        rvEvents = rootView.find(R.id.rv_teams)
        progressBar = rootView.find(R.id.progress_bar)
        adapter = ListTeamAdapter(teams)

        rvEvents.layoutManager = GridLayoutManager(activity, 3)
        rvEvents.adapter = adapter

        setHasOptionsMenu(true)

        presenter.loadListTeam(resources.getString(R.string.league_name))
    }

    override fun attachLayout(): Int {
        return R.layout.fragment_displayteams
    }

    override fun loadingData(status: Boolean) {
        if (status) {
            progressBar.visibility = View.VISIBLE
            rvEvents.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            rvEvents.visibility = View.VISIBLE
        }
    }
}
