package com.qornanali.footballclubkt.feature.favoriteteam

import com.qornanali.footballclubkt.feature.BaseView
import com.qornanali.footballclubkt.model.FavoriteTeam

interface DisplayFavoriteTeamAView : BaseView {

    fun displayActionBarTitle(title: String)
    fun showFavoriteTeams(data: List<FavoriteTeam>)
    fun showError(message: CharSequence?)
}