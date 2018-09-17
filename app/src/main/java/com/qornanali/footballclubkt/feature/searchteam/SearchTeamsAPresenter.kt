package com.qornanali.footballclubkt.feature.searchteam

import com.google.gson.Gson
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TheSportdbAPI
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.Player
import com.qornanali.footballclubkt.model.ResponseGetTeams
import com.qornanali.footballclubkt.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchTeamsAPresenter(gson: Gson,
                            apiRepository: ApiRepository,
                            view: SearchTeamsAView,
                            context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<SearchTeamsAView>(gson, apiRepository, view, context) {


    fun searchTeam(query: String?){
        val repTeamName = query?.replace(" ", "%20")
        view.loadingData(true)
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportdbAPI.searchTeamsByName(repTeamName)), ResponseGetTeams::class.java)
            }
            view.showTeams(data.await().teams)
            view.loadingData(false)
        }
    }
}