package com.qornanali.footballclubkt.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Event(
        @SerializedName("idEvent") val idEvent: String,
        @SerializedName("strHomeTeam") val strHomeTeam: String,
        @SerializedName("strAwayTeam") val strAwayTeam: String,
        @SerializedName("intHomeScore") val intHomeScore: String?,
        @SerializedName("intAwayScore") val intAwayScore: String?,
        @SerializedName("strHomeRedCards") val strHomeRedCards: String?,
        @SerializedName("strHomeYellowCards") val strHomeYellowCards: String?,
        @SerializedName("strHomeLineupGoalkeeper") val strHomeLineupGoalkeeper: String?,
        @SerializedName("strHomeLineupDefense") val strHomeLineupDefense: String?,
        @SerializedName("strHomeLineupMidfield") val strHomeLineupMidfield: String?,
        @SerializedName("strHomeLineupForward") val strHomeLineupForward: String?,
        @SerializedName("strHomeLineupSubstitutes") val strHomeLineupSubstitutes: String?,
        @SerializedName("strHomeFormation") val strHomeFormation: String?,
        @SerializedName("strAwayRedCards") val strAwayRedCards: String?,
        @SerializedName("strAwayYellowCards") val strAwayYellowCards: String?,
        @SerializedName("strAwayLineupGoalkeeper") val strAwayLineupGoalkeeper: String?,
        @SerializedName("strAwayLineupDefense") val strAwayLineupDefense: String?,
        @SerializedName("strAwayLineupMidfield") val strAwayLineupMidfield: String?,
        @SerializedName("strAwayLineupForward") val strAwayLineupForward: String?,
        @SerializedName("strAwayLineupSubstitutes") val strAwayLineupSubstitutes: String?,
        @SerializedName("strAwayFormation") val strAwayFormation: String?,
        @SerializedName("intHomeShots") val intHomeShots: String?,
        @SerializedName("intAwayShots") val intAwayShots: String?,
        @SerializedName("strDate") val strDate: String?,
        @SerializedName("strTime") val strTime: String?,
        @SerializedName("idHomeTeam") val idHomeTeam: String?,
        @SerializedName("idAwayTeam") val idAwayTeam: String?
) : Serializable {

    constructor(favoriteEvent: FavoriteEvent) : this(
            favoriteEvent.idEvent ?: "",
            favoriteEvent.strHomeTeam ?: "",
            favoriteEvent.strAwayTeam ?: "",
            favoriteEvent.intHomeScore,
            favoriteEvent.intAwayScore,
            favoriteEvent.strHomeRedCards,
            favoriteEvent.strHomeYellowCards,
            favoriteEvent.strHomeLineupGoalkeeper,
            favoriteEvent.strHomeLineupDefense,
            favoriteEvent.strHomeLineupMidfield,
            favoriteEvent.strHomeLineupForward,
            favoriteEvent.strHomeLineupSubstitutes,
            favoriteEvent.strHomeFormation,
            favoriteEvent.strAwayRedCards,
            favoriteEvent.strAwayYellowCards,
            favoriteEvent.strAwayLineupGoalkeeper,
            favoriteEvent.strAwayLineupDefense,
            favoriteEvent.strAwayLineupMidfield,
            favoriteEvent.strAwayLineupForward,
            favoriteEvent.strAwayLineupSubstitutes,
            favoriteEvent.strAwayFormation,
            favoriteEvent.intHomeShots,
            favoriteEvent.intAwayShots,
            favoriteEvent.strDate,
            favoriteEvent.strTime,
            favoriteEvent.idHomeTeam,
            favoriteEvent.idAwayTeam)
}