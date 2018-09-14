package com.qornanali.footballclubkt.feature.listleague

import android.content.res.Resources
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TheSportdbAPI
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.ResponseGetLeague
import com.qornanali.footballclubkt.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DisplayListLeagueAPresenter(gson: Gson,
                                  apiRepository: ApiRepository,
                                  view: DisplayListLeagueAView,
                                  context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<DisplayListLeagueAView>(gson, apiRepository, view, context) {

    fun setActionBar(resources: Resources) {
        view.displayActionBarTitle(resources.getString(R.string.choose_league))
    }

    fun loadLeagues() {
        view.loadingData(true)
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportdbAPI.getLeagues()), ResponseGetLeague::class.java)
            }
            view.showLeagues(data.await().leagues)
            view.loadingData(false)
        }
    }
}