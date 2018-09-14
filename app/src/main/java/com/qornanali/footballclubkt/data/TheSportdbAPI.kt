package com.qornanali.footballclubkt.data

import com.qornanali.footballclub_kotlin.BuildConfig

object TheSportdbAPI {

    fun getNextEventsByLeagueId(leagueId: String?): String {
        return BuildConfig.thesportdb_base_url + "${BuildConfig.thesportdb_key}/eventsnextleague.php?id=${leagueId}"
    }

    fun getLastEventsByLeagueId(leagueId: String?): String {
        return BuildConfig.thesportdb_base_url + "${BuildConfig.thesportdb_key}/eventspastleague.php?id=${leagueId}"
    }

    fun getTeamById(teamId: String?): String {
        return BuildConfig.thesportdb_base_url + "${BuildConfig.thesportdb_key}/lookupteam.php?id=${teamId}"
    }

    fun getTeamsByName(teamName: String?): String {
        return BuildConfig.thesportdb_base_url + "${BuildConfig.thesportdb_key}/searchteams.php?t=${teamName}"
    }

    fun geEventsByName(eventName: String?): String {
        return BuildConfig.thesportdb_base_url + "${BuildConfig.thesportdb_key}/searchevents.php?e=${eventName}"
    }

    fun getLeagues(): String {
        return BuildConfig.thesportdb_base_url + "${BuildConfig.thesportdb_key}/all_leagues.php"
    }

    fun getTeamsByLeagueName(leagueName: String?): String {
        return BuildConfig.thesportdb_base_url + "${BuildConfig.thesportdb_key}/search_all_teams.php?l=${leagueName}"
    }
}

