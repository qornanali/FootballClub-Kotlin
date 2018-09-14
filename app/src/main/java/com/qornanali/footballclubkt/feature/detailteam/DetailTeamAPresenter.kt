package com.qornanali.footballclubkt.feature.detailteam

import android.content.res.Resources
import android.support.v4.app.Fragment
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.feature.listplayer.DisplayListPlayerFragment
import com.qornanali.footballclubkt.model.Team
import com.qornanali.footballclubkt.util.CoroutineContextProvider

class DetailTeamAPresenter(gson: Gson,
                           apiRepository: ApiRepository,
                           view: DetailTeamAView,
                           context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<DetailTeamAView>(gson, apiRepository, view, context) {


    fun loadTeamInfo(team: Team?) {
        view.displayActionBarTitle(team?.strTeam)
    }


    fun setTabs(resources: Resources) {
        val titles = ArrayList<String>()
        val fragments = ArrayList<Fragment>()

        titles.add(resources.getString(R.string.info))
        titles.add(resources.getString(R.string.players))

        fragments.add(InfoTeamFragment())
        fragments.add(DisplayListPlayerFragment())

        view.displayTabs(fragments, titles)
    }

}