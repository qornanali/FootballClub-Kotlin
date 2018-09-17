package com.qornanali.footballclubkt.feature.detailteam

import android.support.v4.app.Fragment
import com.qornanali.footballclubkt.feature.BaseView

interface DisplayDetailTeamAView : BaseView {

    fun showError(message: CharSequence?)
    fun displayActionBarTitle(title: String?)
    fun displayTabs(fragments: List<Fragment>, titles: List<String>)
    fun successAddedToFavorite()
    fun successRemovedFromFavorite()
}