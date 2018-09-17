package com.qornanali.footballclubkt.feature.detailteam

import com.qornanali.footballclubkt.feature.BaseView

interface InfoTeamFView : BaseView {

    fun displayManager(manager: String?)
    fun displayDescription(description: String?)
    fun displayCountry(country: String?)
    fun displayName(fullName: String?, shortName: String?, alternate: String?)
    fun displayStadium(stadium: String?)
    fun loadStadiumImage(url: String?)
    fun loadFanart(fanart1Url: String?, fanart2Url: String?)

}