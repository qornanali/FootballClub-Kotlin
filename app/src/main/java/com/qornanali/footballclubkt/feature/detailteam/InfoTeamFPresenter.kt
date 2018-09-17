package com.qornanali.footballclubkt.feature.detailteam

import com.google.gson.Gson
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.Team
import com.qornanali.footballclubkt.util.CoroutineContextProvider

class InfoTeamFPresenter(gson: Gson,
                         apiRepository: ApiRepository,
                         view: InfoTeamFView,
                         context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<InfoTeamFView>(gson, apiRepository, view, context){

    fun loadTeamInfo(team: Team?){
        view.displayCountry(team?.strCountry)
        view.displayManager(team?.strManager)
        view.displayDescription(team?.strDescriptionEN)
        view.displayName(team?.strTeam, team?.strTeamShort, team?.strAlternate)
        view.loadFanart(team?.strTeamFanart1, team?.strTeamFanart3)
        view.loadStadiumImage(team?.strStadiumThumb)
        view.displayStadium(team?.strStadiumDescription)
    }

}