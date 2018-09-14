package com.qornanali.footballclubkt.feature.listevent

import com.google.gson.Gson
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TheSportdbAPI
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.ResponseGetEvents
import com.qornanali.footballclubkt.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DisplayListEventFPresenter(gson: Gson,
                                 apiRepository: ApiRepository,
                                 view: DisplayListEventFView,
                                 context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<DisplayListEventFView>(gson, apiRepository, view, context) {

    fun loadListEvent(title: String?, eventsTypeComparator: String, leagueId: String?) {
        view.loadingData(true)
        val url = if (title.equals(eventsTypeComparator)) TheSportdbAPI.getLastEventsByLeagueId(leagueId) else TheSportdbAPI.getNextEventsByLeagueId(leagueId)
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(url), ResponseGetEvents::class.java)
            }
            view.showListEvent(data.await().events)
            view.loadingData(false)
        }
    }
}
