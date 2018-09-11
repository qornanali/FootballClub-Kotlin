package com.qornanali.footballclubkt.feature.listevent

import com.google.gson.Gson
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TheSportdbAPI
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.ResponseGetEvents
import com.qornanali.footballclubkt.util.CoroutineContextProvider
import com.qornanali.footballclubkt.util.EspressoIdlingResource
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DisplayListEventFPresenter(gson: Gson,
                                 apiRepository: ApiRepository,
                                 view: DisplayListEventFView,
                                 context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<DisplayListEventFView>(gson, apiRepository, view, context) {

    fun loadListEvent(title: String?, eventsTypeComparator: String) {
        view.loadingData(true)
        val url = if (title.equals(eventsTypeComparator)) TheSportdbAPI.getLastEvents("4328") else TheSportdbAPI.getNextEvents("4328")
        EspressoIdlingResource.increment()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(url), ResponseGetEvents::class.java)
            }
            if (!EspressoIdlingResource.idlingResource.isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
            view.showListEvent(data.await().events)
            view.loadingData(false)
        }
    }
}
