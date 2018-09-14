package com.qornanali.footballclubkt.feature.listteam

import com.qornanali.footballclubkt.feature.BaseView
import com.qornanali.footballclubkt.model.Team

interface DisplayListTeamFView : BaseView {

    fun loadingData(status: Boolean)
    fun showListTeam(data: List<Team>)
}