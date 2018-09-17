package com.qornanali.footballclubkt.feature.searchevent

import com.google.gson.Gson
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TheSportdbAPI
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.ResponseGetEvents
import com.qornanali.footballclubkt.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchEventsAPresenter(gson: Gson,
                             apiRepository: ApiRepository,
                             view: SearchEventsAView,
                             context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<SearchEventsAView>(gson, apiRepository, view, context) {


    fun searchEvent(query: String?) {
        val repEventName = query?.replace(" ", "%20")
        view.loadingData(true)
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportdbAPI.searchEventsByName(repEventName)), ResponseGetEvents::class.java)
            }
            view.showEvents(data.await().event)
            view.loadingData(false)
        }
    }
}