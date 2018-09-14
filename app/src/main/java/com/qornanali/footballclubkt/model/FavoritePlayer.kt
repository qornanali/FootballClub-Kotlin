package com.qornanali.footballclubkt.model

data class FavoritePlayer(
        val id: Long?,
        val idTeam: String?,
        val strTeam: String?,
        val strTeamShort: String?,
        val strAlternate: String?,
        val intFormedYear: String?,
        val strManager: String?,
        val strStadium: String?,
        val strStadiumThumb: String?,
        val strStadiumDescription: String?,
        val strStadiumLocation: String?,
        val intStadiumCapacity: String?,
        val strWebsite: String?,
        val strFacebook: String?,
        val strTwitter: String?,
        val strInstagram: String?,
        val strDescriptionEN: String?,
        val strCountry: String?,
        val strTeamBadge: String?,
        val strTeamJersey: String?,
        val strTeamLogo: String?,
        val strTeamFanart1: String?,
        val strTeamFanart2: String?,
        val strTeamFanart3: String?,
        val strTeamFanart4: String?,
        val strTeamBanner: String?,
        val strYoutube: String?) {

    companion object {
        const val TABLE_FAVORITETEAM: String = "TABLE_FAVORITETEAM"
        const val ID: String = "ID_"
        const val FIELD_IDTEAM: String = "FIELD_IDTEAM"
        const val FIELD_NAME: String = "FIELD_NAME"
        const val FIELD_SHORTNAME: String = "FIELD_SHORTNAME"
        const val FIELD_ALTERNATE: String = "FIELD_ALTERNATE"
        const val FIELD_FORMEDYEAR: String = "FIELD_FORMEDYEAR"
        const val FIELD_MANAGER: String = "FIELD_MANAGER"
        const val FIELD_STADIUM: String = "FIELD_STADIUM"
        const val FIELD_STADIUMTHUMB: String = "FIELD_STADIUMTHUMB"
        const val FIELD_STADIUMDESC: String = "FIELD_STADIUMDESC"
        const val FIELD_STADIUMLOCATION: String = "FIELD_STADIUMLOCATION"
        const val FIELD_STADIUMCAPACITY: String = "FIELD_STADIUMCAPACITY"
        const val FIELD_DESCRIPTION: String = "FIELD_DESCRIPTION"
        const val FIELD_COUNTRY: String = "FIELD_COUNTRY"
        const val FIELD_BADGE: String = "FIELD_BADGE"
        const val FIELD_JERSEY: String = "FIELD_JERSEY"
        const val FIELD_LOGO: String = "FIELD_LOGO"
        const val FIELD_FANART1: String = "FIELD_FANART1"
        const val FIELD_FANART2: String = "FIELD_FANART2"
        const val FIELD_FANART3: String = "FIELD_FANART3"
        const val FIELD_FANART4: String = "FIELD_FANART4"
        const val FIELD_BANNER: String = "FIELD_BANNER"
    }
}