package com.qornanali.footballclubkt.feature.detailteam

import android.content.res.Resources
import android.database.sqlite.SQLiteConstraintException
import android.support.v4.app.Fragment
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.SQLiteHelper
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.feature.listplayer.DisplayListPlayerFragment
import com.qornanali.footballclubkt.model.FavoriteTeam
import com.qornanali.footballclubkt.model.Team
import com.qornanali.footballclubkt.util.CoroutineContextProvider
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert

class DisplayDetailTeamAPresenter(gson: Gson,
                                  apiRepository: ApiRepository,
                                  viewDisplay: DisplayDetailTeamAView,
                                  context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<DisplayDetailTeamAView>(gson, apiRepository, viewDisplay, context) {


    fun loadTeamInfo(team: Team?) {
        view.displayActionBarTitle(team?.strTeam)
    }


    fun setTabs(resources: Resources) {
        val titles = ArrayList<String>()
        val fragments = ArrayList<Fragment>()

        titles.add(resources.getString(R.string.info))
        titles.add(resources.getString(R.string.players))

        fragments.add(InfoTeamFragment())
        fragments.add(DisplayListPlayerFragment())

        view.displayTabs(fragments, titles)
    }


    fun removeFromFavorite(idTeam: String?, database: SQLiteHelper) {
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITETEAM,
                        "(FIELD_IDTEAM = {value})",
                        "value" to (idTeam ?: ""))
            }
            view.successRemovedFromFavorite()
        } catch (e: SQLiteConstraintException) {
            view.showError(e.message)
        }
    }

    fun addToFavorite(team: Team?, database: SQLiteHelper) {
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITETEAM,
                        FavoriteTeam.FIELD_IDTEAM to team?.idTeam,
                        FavoriteTeam.FIELD_NAME to team?.strTeam,
                        FavoriteTeam.FIELD_SHORTNAME to team?.strTeamShort,
                        FavoriteTeam.FIELD_ALTERNATE to team?.strAlternate,
                        FavoriteTeam.FIELD_FORMEDYEAR to team?.intFormedYear,
                        FavoriteTeam.FIELD_MANAGER to team?.strManager,
                        FavoriteTeam.FIELD_STADIUM to team?.strStadium,
                        FavoriteTeam.FIELD_STADIUMTHUMB to team?.strStadiumThumb,
                        FavoriteTeam.FIELD_STADIUMDESC to team?.strStadiumDescription,
                        FavoriteTeam.FIELD_STADIUMLOCATION to team?.strStadiumLocation,
                        FavoriteTeam.FIELD_STADIUMCAPACITY to team?.intStadiumCapacity,
                        FavoriteTeam.FIELD_DESCRIPTION to team?.strDescriptionEN,
                        FavoriteTeam.FIELD_COUNTRY to team?.strCountry,
                        FavoriteTeam.FIELD_BADGE to team?.strTeamBadge,
                        FavoriteTeam.FIELD_JERSEY to team?.strTeamJersey,
                        FavoriteTeam.FIELD_LOGO to team?.strTeamLogo,
                        FavoriteTeam.FIELD_FANART1 to team?.strTeamFanart1,
                        FavoriteTeam.FIELD_FANART2 to team?.strTeamFanart2,
                        FavoriteTeam.FIELD_FANART3 to team?.strTeamFanart3,
                        FavoriteTeam.FIELD_FANART4 to team?.strTeamFanart4,
                        FavoriteTeam.FIELD_BANNER to team?.strTeamBanner)
            }
            view.successAddedToFavorite()
        } catch (e: SQLiteConstraintException) {
            view.showError(e.message)
        }
    }

}