package com.qornanali.footballclubkt.feature.searchevent

import com.qornanali.footballclubkt.feature.BaseView
import com.qornanali.footballclubkt.model.Event
import com.qornanali.footballclubkt.model.Team

interface SearchEventsAView : BaseView {

    fun loadingData(status: Boolean)
    fun showEvents(data: List<Event>)
}