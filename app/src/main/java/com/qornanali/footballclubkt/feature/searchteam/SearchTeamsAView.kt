package com.qornanali.footballclubkt.feature.searchteam

import com.qornanali.footballclubkt.feature.BaseView
import com.qornanali.footballclubkt.model.Team

interface SearchTeamsAView : BaseView {

    fun loadingData(status: Boolean)
    fun showTeams(data: List<Team>)
}