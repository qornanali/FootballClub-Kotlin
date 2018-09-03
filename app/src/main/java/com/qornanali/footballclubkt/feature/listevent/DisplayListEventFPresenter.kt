package com.qornanali.footballclubkt.feature.listevent

import android.content.res.Resources
import android.os.Bundle
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TheSportdbAPI
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.ResponseGetEvents
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DisplayListEventFPresenter: BasePresenter<DisplayListEventFView>(){

    fun loadListEvent(arguments: Bundle?, resources: Resources){
        view.loadingData(true)
        arguments?.takeIf { it.containsKey("title") }?.apply {
            var url = if (getString("title").equals(resources.getString(R.string.last_events))) TheSportdbAPI.getLastEvents("4328") else TheSportdbAPI.getNextEvents("4328")
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
}