package com.qornanali.footballclubkt.feature.listplayer

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.ListPlayerAdapter
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.feature.BaseFragment
import com.qornanali.footballclubkt.feature.detailplayer.DisplayDetailPlayerActivity
import com.qornanali.footballclubkt.feature.detailteam.DetailTeamActivity
import com.qornanali.footballclubkt.feature.favoritevent.DisplayFavoriteEventsActivity
import com.qornanali.footballclubkt.model.Player
import com.qornanali.footballclubkt.util.OnItemClickListener
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class DisplayListPlayerFragment :
        BaseFragment<DisplayListPlayerFPresenter, DisplayListPlayerFView>(),
        DisplayListPlayerFView {

    private lateinit var rvPlayers: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: ListPlayerAdapter
    private val players = ArrayList<Player>()

    override fun attachPresenter(): DisplayListPlayerFPresenter {
        return DisplayListPlayerFPresenter(Gson(), ApiRepository(), this)
    }

    override fun showListPlayer(data: List<Player>) {
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onInitializeViews() {
        rvPlayers = rootView.find(R.id.rv_players)
        progressBar = rootView.find(R.id.progress_bar)
        adapter = ListPlayerAdapter(players, OnItemClickListener {
            activity?.startActivity<DisplayDetailPlayerActivity>("player" to it)
        })

        rvPlayers.layoutManager = LinearLayoutManager(activity)
        rvPlayers.adapter = adapter

        presenter.loadListPlayer((activity as DetailTeamActivity).team?.idTeam)
    }

    override fun attachLayout(): Int {
        return R.layout.fragment_displayplayers
    }

    override fun loadingData(status: Boolean) {
        if (status) {
            progressBar.visibility = View.VISIBLE
            rvPlayers.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            rvPlayers.visibility = View.VISIBLE
        }
    }

}
