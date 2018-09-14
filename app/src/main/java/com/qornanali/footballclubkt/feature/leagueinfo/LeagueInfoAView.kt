package com.qornanali.footballclubkt.feature.leagueinfo

import android.support.v4.app.Fragment
import com.qornanali.footballclubkt.feature.BaseView

interface LeagueInfoAView : BaseView {

    fun displayActionBarTitle(title: String)
    fun displayTabs(fragments: List<Fragment>, titles: List<String>)
}