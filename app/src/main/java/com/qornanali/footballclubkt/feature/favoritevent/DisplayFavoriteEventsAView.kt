package com.qornanali.footballclubkt.feature.favoritevent

import com.qornanali.footballclubkt.feature.BaseView
import com.qornanali.footballclubkt.model.FavoriteEvent

interface DisplayFavoriteEventsAView : BaseView {

    fun displayActionBarTitle(title: String)
    fun showFavoriteEvents(data: List<FavoriteEvent>)
    fun showError(message: CharSequence?)
}