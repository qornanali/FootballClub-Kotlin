package com.qornanali.footballclubkt.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Team(
        @SerializedName("idTeam") val idTeam: String?,
        @SerializedName("strTeam") val strTeam: String?,
        @SerializedName("strTeamShort") val strTeamShort: String?,
        @SerializedName("strAlternate") val strAlternate: String?,
        @SerializedName("intFormedYear") val intFormedYear: String?,
        @SerializedName("strManager") val strManager: String?,
        @SerializedName("strStadium") val strStadium: String?,
        @SerializedName("strStadiumThumb") val strStadiumThumb: String?,
        @SerializedName("strStadiumDescription") val strStadiumDescription: String?,
        @SerializedName("strStadiumLocation") val strStadiumLocation: String?,
        @SerializedName("intStadiumCapacity") val intStadiumCapacity: String?,
        @SerializedName("strDescriptionEN") val strDescriptionEN: String?,
        @SerializedName("strCountry") val strCountry: String?,
        @SerializedName("strTeamBadge") val strTeamBadge: String?,
        @SerializedName("strTeamJersey") val strTeamJersey: String?,
        @SerializedName("strTeamLogo") val strTeamLogo: String?,
        @SerializedName("strTeamFanart1") val strTeamFanart1: String?,
        @SerializedName("strTeamFanart2") val strTeamFanart2: String?,
        @SerializedName("strTeamFanart3") val strTeamFanart3: String?,
        @SerializedName("strTeamFanart4") val strTeamFanart4: String?,
        @SerializedName("strTeamBanner") val strTeamBanner: String?
) : Serializable {

    constructor(favoriteTeam: FavoriteTeam) : this(
            favoriteTeam.idTeam,
            favoriteTeam.strTeam,
            favoriteTeam.strTeamShort,
            favoriteTeam.strAlternate,
            favoriteTeam.intFormedYear,
            favoriteTeam.strManager,
            favoriteTeam.strStadium,
            favoriteTeam.strStadiumThumb,
            favoriteTeam.strStadiumDescription,
            favoriteTeam.strStadiumLocation,
            favoriteTeam.intStadiumCapacity,
            favoriteTeam.strDescriptionEN,
            favoriteTeam.strCountry,
            favoriteTeam.strTeamBadge,
            favoriteTeam.strTeamJersey,
            favoriteTeam.strTeamLogo,
            favoriteTeam.strTeamFanart1,
            favoriteTeam.strTeamFanart2,
            favoriteTeam.strTeamFanart3,
            favoriteTeam.strTeamFanart4,
            favoriteTeam.strTeamBanner
    )
}