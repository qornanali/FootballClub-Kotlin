package com.qornanali.footballclubkt.data

import com.qornanali.footballclub_kotlin.BuildConfig

object TheSportdbAPI {

    fun getNextEvents(leagueId: String?): String {
        return BuildConfig.thesportdb_base_url + "${BuildConfig.thesportdb_key}/eventsnextleague.php?id=${leagueId}"
    }

    fun getLastEvents(leagueId: String?): String {
        return BuildConfig.thesportdb_base_url + "${BuildConfig.thesportdb_key}/eventspastleague.php?id=${leagueId}"
    }

    fun getTeamDetail(teamId: String?): String {
        return BuildConfig.thesportdb_base_url + "${BuildConfig.thesportdb_key}/lookupteam.php?id=${teamId}"
    }
}

