package com.qornanali.footballclubkt.model

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("idTeam") val idTeam: String?,
        @SerializedName("strTeam") val strTeam: String?,
        @SerializedName("strTeamShort") val strTeamShort: String?,
        @SerializedName("strAlternate") val strAlternate: String?,
        @SerializedName("intFormedYear") val intFormedYear: String?,
        @SerializedName("strManager") val strManager: String?,
        @SerializedName("strStadium") val strStadium: String?,
        @SerializedName("strKeywords") val strKeywords: String?,
        @SerializedName("strStadiumThumb") val strStadiumThumb: String?,
        @SerializedName("strStadiumDescription") val strStadiumDescription: String?,
        @SerializedName("strStadiumLocation") val strStadiumLocation: String?,
        @SerializedName("intStadiumCapacity") val intStadiumCapacity: String?,
        @SerializedName("strWebsite") val strWebsite: String?,
        @SerializedName("strFacebook") val strFacebook: String?,
        @SerializedName("strTwitter") val strTwitter: String?,
        @SerializedName("strInstagram") val strInstagram: String?,
        @SerializedName("strDescriptionEN") val strDescriptionEN: String?,
        @SerializedName("strCountry") val strCountry: String?,
        @SerializedName("strTeamBadge") val strTeamBadge: String?,
        @SerializedName("strTeamJersey") val strTeamJersey: String?,
        @SerializedName("strTeamLogo") val strTeamLogo: String?,
        @SerializedName("strTeamFanart1") val strTeamFanart1: String?,
        @SerializedName("strTeamFanart2") val strTeamFanart2: String?,
        @SerializedName("strTeamFanart3") val strTeamFanart3: String?,
        @SerializedName("strTeamFanart4") val strTeamFanart4: String?,
        @SerializedName("strTeamBanner") val strTeamBanner: String?,
        @SerializedName("strYoutube") val strYoutube: String?
)