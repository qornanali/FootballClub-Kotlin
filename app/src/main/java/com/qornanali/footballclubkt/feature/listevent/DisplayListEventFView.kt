package com.qornanali.footballclubkt.feature.listevent

import com.qornanali.footballclubkt.feature.BaseView
import com.qornanali.footballclubkt.model.Event

interface DisplayListEventFView  : BaseView {

    fun loadingData(status: Boolean)
    fun showListEvent(data: List<Event>)
}