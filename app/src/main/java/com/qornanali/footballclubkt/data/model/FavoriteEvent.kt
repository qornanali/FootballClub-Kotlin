package com.qornanali.footballclubkt.data.model

data class FavoriteEvent(
        val idEvent: Long?,
        val strHomeTeam: String?,
        val strAwayTeam: String?,
        val intHomeScore: String?,
        val intAwayScore: String?,
        val strHomeRedCards: String?,
        val strHomeYellowCards: String?,
        val strHomeLineupGoalkeeper: String?,
        val strHomeLineupDefense: String?,
        val strHomeLineupMidfield: String?,
        val strHomeLineupForward: String?,
        val strHomeLineupSubstitutes: String?,
        val strHomeFormation: String?,
        val strAwayRedCards: String?,
        val strAwayYellowCards: String?,
        val strAwayLineupGoalkeeper: String?,
        val strAwayLineupDefense: String?,
        val strAwayLineupMidfield: String?,
        val strAwayLineupForward: String?,
        val strAwayLineupSubstitutes: String?,
        val strAwayFormation: String?,
        val intHomeShots: String?,
        val intAwayShots: String?,
        val strDate: String?,
        val strTime: String?,
        val idHomeTeam: String?,
        val idAwayTeam: String?,
        val strHomeBadge: String?,
        val strAwayBadge: String?) {

    companion object {
        const val TABLE_FAVORITEEVENT: String = "TABLE_FAVORITEEVENT"
        const val ID: String = "ID_"
        const val FIELD_IDEVENT: String = "FIELD_IDEVENT"
        const val FIELD_HOMETEAM: String = "FIELD_HOMETEAM"
        const val FIELD_AWAYTEAM: String = "FIELD_AWAYTEAM"
        const val FIELD_HOMESCORE: String = "FIELD_HOMESCORE"
        const val FIELD_AWAYSCORE: String = "FIELD_AWAYSCORE"
        const val FIELD_HOMERED: String = "FIELD_HOMERED"
        const val FIELD_HOMEYELLOW: String = "FIELD_HOMEYELLOW"
        const val FIELD_HOMEGK: String = "FIELD_HOMEGK"
        const val FIELD_HOMEDF: String = "FIELD_HOMEDF"
        const val FIELD_HOMEMF: String = "FIELD_HOMEMF"
        const val FIELD_HOMEFW: String = "FIELD_HOMEFW"
        const val FIELD_HOMESUB: String = "FIELD_HOMESUB"
        const val FIELD_HOMEFORMATION: String = "FIELD_HOMEFORMATION"
        const val FIELD_AWAYRED: String = "FIELD_AWAYRED"
        const val FIELD_AWAYYELLOW: String = "FIELD_AWAYYELLOW"
        const val FIELD_AWAYGK: String = "FIELD_AWAYGK"
        const val FIELD_AWAYDF: String = "FIELD_AWAYDF"
        const val FIELD_AWAYMF: String = "FIELD_AWAYMF"
        const val FIELD_AWAYFW: String = "FIELD_AWAYFW"
        const val FIELD_AWAYSUB: String = "FIELD_AWAYSUB"
        const val FIELD_AWAYFORMATION: String = "FIELD_AWAYFORMATION"
        const val FIELD_HOMESHOTS: String = "FIELD_HOMESHOTS"
        const val FIELD_AWAYSHOTS: String = "FIELD_AWAYSHOTS"
        const val FIELD_DATE: String = "FIELD_DATE"
        const val FIELD_TIME: String = "FIELD_TIME"
        const val FIELD_HOMEID: String = "FIELD_HOMEID"
        const val FIELD_AWAYID: String = "FIELD_AWAYID"
        const val FIELD_HOMEBADGE: String = "FIELD_HOMEBADGE"
        const val FIELD_AWAYBADGE: String = "FIELD_AWAYBADGE"
    }
}