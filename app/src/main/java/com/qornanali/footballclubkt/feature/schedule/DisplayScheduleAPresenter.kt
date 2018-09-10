package com.qornanali.footballclubkt.feature.schedule

import android.content.res.Resources
import android.support.v4.app.Fragment
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.feature.listevent.LastEventFragment
import com.qornanali.footballclubkt.feature.listevent.NextEventFragment
import com.qornanali.footballclubkt.util.CoroutineContextProvider

class DisplayScheduleAPresenter(gson: Gson,
                                apiRepository: ApiRepository,
                                view: DisplayScheduleAView,
                                context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<DisplayScheduleAView>(gson, apiRepository, view, context) {

    fun setActionBar(resources: Resources) {
        view.displayActionBarTitle(resources.getString(R.string.league_name))
    }

    fun setTabs(resources: Resources) {
        val titles = ArrayList<String>()
        val fragments = ArrayList<Fragment>()

        titles.add(resources.getString(R.string.next_events))
        titles.add(resources.getString(R.string.last_events))

        fragments.add(NextEventFragment())
        fragments.add(LastEventFragment())

        view.displayTabs(fragments, titles)
    }

}