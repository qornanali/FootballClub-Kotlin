package com.qornanali.footballclubkt.feature.listleague

import com.qornanali.footballclubkt.feature.BaseView
import com.qornanali.footballclubkt.model.League

interface DisplayListLeagueAView : BaseView {

    fun displayActionBarTitle(title: String)
    fun showLeagues(data: List<League>)
    fun loadingData(status: Boolean)
}