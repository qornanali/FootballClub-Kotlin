package com.qornanali.footballclubkt.feature.detailevent

import com.qornanali.footballclubkt.feature.BaseView
import com.qornanali.footballclubkt.model.Statistic
import com.squareup.picasso.Picasso

interface DisplayDetailEventAView  : BaseView {

    fun showError(message: CharSequence?)
    fun showScores(awayScore: String, homeScore: String)
    fun showAwayBadge(imageUrl: String?)
    fun showHomeBadge(imageUrl: String?)
    fun showTeamName(awayName: String, homeName: String)
    fun showEventDate(date: String)
    fun showStatistic(data: List<Statistic>)
    fun successAddedToFavorite()
    fun successRemovedFromFavorite()
    fun displayActionBarTitle(title: String)
}