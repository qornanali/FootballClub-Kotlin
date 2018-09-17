package com.qornanali.footballclubkt.feature.detailplayer

import com.qornanali.footballclubkt.feature.BaseView

interface DisplayDetailPlayerAView : BaseView {

    fun displayActionBarTitle(title: String?)
    fun displayInfo(name: String?, height: String?, weight: String?)
    fun displayTeam(name: String?, position: String?, wage: String?)
    fun displaySigned(signingDate: String?, signingPrice: String?)
    fun displayDesc(description: String?)
    fun loadFanart(fanart: String?)
    fun loadCutout(cutout: String?)
}