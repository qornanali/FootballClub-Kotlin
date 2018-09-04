package com.qornanali.footballclubkt.feature.detailevent

import com.qornanali.footballclubkt.feature.BaseView
import com.qornanali.footballclubkt.model.Statistic

interface DisplayDetailEventAView : BaseView {

    fun showError(message: CharSequence?)
    fun showScores(awayScore: String, homeScore: String)
    fun showAwayBadges(imageUrl: String)
    fun showHomeBadges(imageUrl: String)
    fun showTeamName(awayName: String, homeName: String)
    fun showEventDate(date: String)
    fun showStatistic(data: List<Statistic>)
    fun successAddedToFavorite()
    fun successRemovedFromFavorite()
    fun displayActionBarTitle(title: String)
}