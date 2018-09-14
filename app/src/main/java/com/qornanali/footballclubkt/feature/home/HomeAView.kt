package com.qornanali.footballclubkt.feature.home

import android.support.v4.app.Fragment
import com.qornanali.footballclubkt.feature.BaseView

interface HomeAView : BaseView {

    fun displayActionBarTitle(title: String?)
    fun displayTabs(fragments: List<Fragment>, titles: List<String>)
}