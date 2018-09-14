package com.qornanali.footballclubkt.feature.listleague

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.ListLeagueAdapter
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.feature.BaseActivity
import com.qornanali.footballclubkt.feature.home.HomeActivity
import com.qornanali.footballclubkt.model.League
import com.qornanali.footballclubkt.util.OnItemClickListener
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


class DisplayListLeagueActivity :
        BaseActivity<DisplayListLeagueAPresenter, DisplayListLeagueAView>(),
        DisplayListLeagueAView {

    private lateinit var toolbar: Toolbar
    private lateinit var rvLeagues: RecyclerView
    private lateinit var adapter: ListLeagueAdapter
    private lateinit var progressBar: ProgressBar
    private val leagues = ArrayList<League>()

    override fun showLeagues(data: List<League>) {
        leagues.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun loadingData(status: Boolean) {
        if (status) {
            progressBar.visibility = View.VISIBLE
            rvLeagues.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            rvLeagues.visibility = View.VISIBLE
        }
    }

    override fun onInitializeViews() {
        toolbar = find(R.id.toolbar)
        rvLeagues = find(R.id.rv_leagues)
        progressBar = find(R.id.progress_bar)

        setSupportActionBar(toolbar)

        adapter = ListLeagueAdapter(leagues, OnItemClickListener {
            startActivity<HomeActivity>("league" to it)
        })

        rvLeagues.layoutManager = LinearLayoutManager(this)
        rvLeagues.adapter = adapter

        presenter.setActionBar(resources)

        presenter.loadLeagues()
    }

    override fun attachPresenter(): DisplayListLeagueAPresenter {
        return DisplayListLeagueAPresenter(Gson(), ApiRepository(), this)
    }

    override fun displayActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun attachLayout(): Int {
        return R.layout.activity_displayleagues
    }

}
