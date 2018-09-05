package com.qornanali.footballclubkt.feature.listevent

import com.google.gson.Gson
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TheSportdbAPI
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.ResponseGetEvents
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DisplayListEventFPresenter : BasePresenter<DisplayListEventFView>() {

    fun loadListEvent(title: String?, eventsTypeComparator: String) {
        view.loadingData(true)
        val url = if (title.equals(eventsTypeComparator)) TheSportdbAPI.getLastEvents("4328") else TheSportdbAPI.getNextEvents("4328")
        doAsync {
            val data = Gson().fromJson(ApiRepository()
                    .doRequest(url), ResponseGetEvents::class.java)
            uiThread {
                view.showListEvent(data.events)
                view.loadingData(false)
            }
        }
    }
}
