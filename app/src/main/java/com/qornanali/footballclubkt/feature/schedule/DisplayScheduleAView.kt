package com.qornanali.footballclubkt.feature.schedule

import android.support.v4.app.Fragment
import com.qornanali.footballclubkt.feature.BaseView

interface DisplayScheduleAView : BaseView {

    fun displayActionBarTitle(title: String)
    fun displayTabs(fragments: List<Fragment>, titles: List<String>)
}