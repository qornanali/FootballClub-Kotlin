package com.qornanali.footballclubkt.feature.detailevent

import android.content.res.Resources
import android.database.sqlite.SQLiteConstraintException
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.SQLiteHelper
import com.qornanali.footballclubkt.data.TheSportdbAPI
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.Event
import com.qornanali.footballclubkt.model.FavoriteEvent
import com.qornanali.footballclubkt.model.ResponseGetTeams
import com.qornanali.footballclubkt.model.Statistic
import com.qornanali.footballclubkt.util.DateFormatter
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DisplayDetailEventAPresenter : BasePresenter<DisplayDetailEventAView>() {

    fun setActionBar(resources: Resources) {
        view.displayActionBarTitle(resources.getString(R.string.my_favorites))
    }

    fun loadEventDate(strDate: String?, strTime: String?){
        val eventDate = DateFormatter.formatToDate(strDate + " " + strTime?.split("+")?.get(0), "dd/MM/yy HH:mm:ss")
        view.showEventDate(DateFormatter.formatToString(eventDate, "EEE, MMMM yyyy HH:mm"))
    }

    fun loadTeamsName(awayName : String, homeName: String){
        view.showTeamName(awayName, homeName)
    }


    fun removeFromFavorite(event: Event, database: SQLiteHelper) {
        try {
            database.use {
                delete(FavoriteEvent.TABLE_FAVORITEEVENT,
                        "(FIELD_IDEVENT = {value})",
                        "value" to event.idEvent)
            }
            view.successRemovedFromFavorite()
        } catch (e: SQLiteConstraintException) {
            view.showError(e.message)
        }
    }

    fun addToFavorite(event: Event, database: SQLiteHelper) {
        try {
            database.use {
                insert(FavoriteEvent.TABLE_FAVORITEEVENT,
                        FavoriteEvent.FIELD_IDEVENT to event.idEvent,
                        FavoriteEvent.FIELD_HOMETEAM to event.strHomeTeam,
                        FavoriteEvent.FIELD_AWAYTEAM to event.strAwayTeam,
                        FavoriteEvent.FIELD_HOMESCORE to event.intHomeScore,
                        FavoriteEvent.FIELD_AWAYSCORE to event.intAwayScore,
                        FavoriteEvent.FIELD_HOMERED to event.strHomeRedCards,
                        FavoriteEvent.FIELD_HOMEYELLOW to event.strHomeYellowCards,
                        FavoriteEvent.FIELD_HOMEGK to event.strHomeLineupGoalkeeper,
                        FavoriteEvent.FIELD_HOMEDF to event.strHomeLineupDefense,
                        FavoriteEvent.FIELD_HOMEMF to event.strHomeLineupMidfield,
                        FavoriteEvent.FIELD_HOMEFW to event.strHomeLineupForward,
                        FavoriteEvent.FIELD_HOMESUB to event.strHomeLineupSubstitutes,
                        FavoriteEvent.FIELD_HOMEFORMATION to event.strHomeFormation,
                        FavoriteEvent.FIELD_AWAYRED to event.strAwayRedCards,
                        FavoriteEvent.FIELD_AWAYYELLOW to event.strAwayYellowCards,
                        FavoriteEvent.FIELD_AWAYGK to event.strAwayLineupGoalkeeper,
                        FavoriteEvent.FIELD_AWAYDF to event.strAwayLineupDefense,
                        FavoriteEvent.FIELD_AWAYMF to event.strAwayLineupMidfield,
                        FavoriteEvent.FIELD_AWAYFW to event.strAwayLineupForward,
                        FavoriteEvent.FIELD_AWAYSUB to event.strAwayLineupSubstitutes,
                        FavoriteEvent.FIELD_AWAYFORMATION to event.strAwayFormation,
                        FavoriteEvent.FIELD_HOMESHOTS to event.intHomeShots,
                        FavoriteEvent.FIELD_AWAYSHOTS to event.intAwayShots,
                        FavoriteEvent.FIELD_DATE to event.strDate,
                        FavoriteEvent.FIELD_TIME to event.strTime,
                        FavoriteEvent.FIELD_HOMEID to event.idHomeTeam,
                        FavoriteEvent.FIELD_AWAYID to event.idAwayTeam)
            }
            view.successAddedToFavorite()
        } catch (e: SQLiteConstraintException) {
            view.showError(e.message)
        }
    }

    fun loadStatistic(event: Event, resources: Resources) {
        val data = ArrayList<Statistic>()
        data.add(Statistic(event.strHomeFormation, resources.getString(R.string.formation), event.strAwayFormation))
        data.add(Statistic(event.intHomeShots, resources.getString(R.string.shots), event.intAwayShots))
        data.add(Statistic(event.strHomeRedCards, resources.getString(R.string.red_cards), event.strAwayRedCards))
        data.add(Statistic(event.strHomeYellowCards, resources.getString(R.string.yellow_cards), event.strAwayYellowCards))
        data.add(Statistic(event.strHomeLineupForward, resources.getString(R.string.forward), event.strAwayLineupForward))
        data.add(Statistic(event.strHomeLineupMidfield, resources.getString(R.string.midfielder), event.strAwayLineupMidfield))
        data.add(Statistic(event.strHomeLineupDefense, resources.getString(R.string.defender), event.strAwayLineupDefense))
        data.add(Statistic(event.strHomeLineupGoalkeeper, resources.getString(R.string.goalkeeper), event.strAwayLineupGoalkeeper))
        data.add(Statistic(event.strHomeLineupSubstitutes, resources.getString(R.string.subtitutes), event.strAwayLineupSubstitutes))
        view.showStatistic(data)
    }

    fun loadHomeBadge(idHomeTeam: String?) {
        doAsync {
            val data = Gson().fromJson(ApiRepository()
                    .doRequest(TheSportdbAPI.getTeamDetail(idHomeTeam)), ResponseGetTeams::class.java)
            uiThread {
                view.showHomeBadges(data.teams.get(0).strTeamBadge)
            }
        }
    }

    fun loadAwayBadge(idAwayTeam: String?) {
        doAsync {
            val data = Gson().fromJson(ApiRepository()
                    .doRequest(TheSportdbAPI.getTeamDetail(idAwayTeam)), ResponseGetTeams::class.java)
            uiThread {
                view.showAwayBadges(data.teams.get(0).strTeamBadge)
            }
        }
    }
}