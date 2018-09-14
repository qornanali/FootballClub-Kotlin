package com.qornanali.footballclubkt.feature.listplayer

import com.qornanali.footballclubkt.feature.BaseView
import com.qornanali.footballclubkt.model.Player

interface DisplayListPlayerFView : BaseView {

    fun loadingData(status: Boolean)
    fun showListPlayer(data: List<Player>)
}