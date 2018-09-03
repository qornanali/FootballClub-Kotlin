package com.qornanali.footballclubkt.feature.schedule

import android.content.res.Resources
import android.support.v4.app.Fragment
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.feature.listevent.DisplayListEventFragment

class DisplayScheduleAPresenter() : BasePresenter<DisplayScheduleAView>() {

    fun setActionBar(resources: Resources) {
        view.displayActionBarTitle(resources.getString(R.string.league_name))
    }

    fun setTabs(resources: Resources) {
        val titles = ArrayList<String>()
        val fragments = ArrayList<Fragment>()

        titles.add(resources.getString(R.string.next_events))
        titles.add(resources.getString(R.string.last_events))

        fragments.add(DisplayListEventFragment())
        fragments.add(DisplayListEventFragment())

        view.displayTabs(fragments, titles)
    }

}