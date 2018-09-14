package com.qornanali.footballclubkt.feature.listplayer

import com.google.gson.Gson
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TheSportdbAPI
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.ResponseGetPlayers
import com.qornanali.footballclubkt.model.ResponseGetTeams
import com.qornanali.footballclubkt.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DisplayListPlayerFPresenter(gson: Gson,
                                  apiRepository: ApiRepository,
                                  view: DisplayListPlayerFView,
                                  context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<DisplayListPlayerFView>(gson, apiRepository, view, context) {

    fun loadListPlayer(teamId: String?) {
        view.loadingData(true)
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportdbAPI.getPlayersByTeamId(teamId)), ResponseGetPlayers::class.java)
            }
            view.showListPlayer(data.await().players)
            view.loadingData(false)
        }
    }
}
