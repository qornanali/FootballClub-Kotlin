package com.qornanali.footballclubkt.feature.listteam

import com.google.gson.Gson
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TheSportdbAPI
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.ResponseGetTeams
import com.qornanali.footballclubkt.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DisplayListTeamFPresenter(gson: Gson,
                                apiRepository: ApiRepository,
                                view: DisplayListTeamFView,
                                context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<DisplayListTeamFView>(gson, apiRepository, view, context) {

    fun loadListTeam(leagueName: String?) {
        val repLeagueName = leagueName?.replace(" ", "%20")
        view.loadingData(true)
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportdbAPI.getTeamsByLeagueName(repLeagueName)), ResponseGetTeams::class.java)
            }
            view.showListTeam(data.await().teams)
            view.loadingData(false)
        }
    }
}
