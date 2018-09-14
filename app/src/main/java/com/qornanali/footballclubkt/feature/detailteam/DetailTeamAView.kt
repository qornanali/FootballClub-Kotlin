package com.qornanali.footballclubkt.feature.detailteam

import android.support.v4.app.Fragment
import com.qornanali.footballclubkt.feature.BaseView

interface DetailTeamAView : BaseView {

    fun displayActionBarTitle(title: String?)
    fun displayTabs(fragments: List<Fragment>, titles: List<String>)
}