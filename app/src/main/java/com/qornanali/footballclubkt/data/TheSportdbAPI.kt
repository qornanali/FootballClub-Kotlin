package com.qornanali.footballclubkt.data

import android.net.Uri
import com.qornanali.footballclub_kotlin.BuildConfig

object TheSportdbAPI {

    fun getNextEvents(leagueId: String?): String {
        return Uri.parse(BuildConfig.thesportdb_base_url).buildUpon()
                .appendPath(BuildConfig.thesportdb_key)
                .appendPath("eventsnextleague.php")
                .appendQueryParameter("id", leagueId)
                .build().toString()
    }

    fun getLastEvents(leagueId: String?): String {
        return Uri.parse(BuildConfig.thesportdb_base_url).buildUpon()
                .appendPath(BuildConfig.thesportdb_key)
                .appendPath("eventspastleague.php")
                .appendQueryParameter("id", leagueId)
                .build().toString()
    }

    fun getTeamDetail(teamId: String?):String {
        return Uri.parse(BuildConfig.thesportdb_base_url).buildUpon()
                .appendPath(BuildConfig.thesportdb_key)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id", teamId)
                .build().toString()
    }
}

