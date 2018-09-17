package com.qornanali.footballclubkt.feature.detailplayer

import com.google.gson.Gson
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.Player
import com.qornanali.footballclubkt.util.CoroutineContextProvider

class DisplayDetailPlayerAPresenter(gson: Gson,
                                    apiRepository: ApiRepository,
                                    view: DisplayDetailPlayerAView,
                                    context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<DisplayDetailPlayerAView>(gson, apiRepository, view, context) {

    fun loadPlayerInfo(player: Player?) {
        view.displayActionBarTitle(player?.strPlayer)
        view.displayInfo(player?.strPlayer, player?.strHeight, player?.strWeight)
        view.displayDesc(player?.strDescriptionEN)
        view.displaySigned(player?.dateSigned, player?.strSigning)
        view.displayTeam(player?.strTeam, player?.strPosition, player?.strWage)
        view.loadCutout(player?.strCutout)
        view.loadFanart(player?.strFanart1)
    }
}