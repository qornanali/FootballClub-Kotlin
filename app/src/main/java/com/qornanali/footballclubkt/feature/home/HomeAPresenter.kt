package com.qornanali.footballclubkt.feature.home

import android.content.res.Resources
import android.support.v4.app.Fragment
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.feature.listevent.DisplayListEventFragment
import com.qornanali.footballclubkt.feature.listteam.DisplayListTeamFragment
import com.qornanali.footballclubkt.util.CoroutineContextProvider

class HomeAPresenter(gson: Gson,
                     apiRepository: ApiRepository,
                     view: HomeAView,
                     context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<HomeAView>(gson, apiRepository, view, context) {

    fun setActionBar(resources: Resources) {
        view.displayActionBarTitle(resources.getString(R.string.league_name))
    }

    fun setTabs(resources: Resources) {
        val titles = ArrayList<String>()
        val fragments = ArrayList<Fragment>()

        titles.add(resources.getString(R.string.next_events))
        titles.add(resources.getString(R.string.last_events))
        titles.add(resources.getString(R.string.teams))

        fragments.add(DisplayListEventFragment())
        fragments.add(DisplayListEventFragment())
        fragments.add(DisplayListTeamFragment())

        view.displayTabs(fragments, titles)
    }

}