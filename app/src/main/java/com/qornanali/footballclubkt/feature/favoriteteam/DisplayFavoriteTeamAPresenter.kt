package com.qornanali.footballclubkt.feature.favoriteteam

import android.content.res.Resources
import android.database.sqlite.SQLiteConstraintException
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.SQLiteHelper
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.FavoriteTeam
import com.qornanali.footballclubkt.util.CoroutineContextProvider
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class DisplayFavoriteTeamAPresenter(gson: Gson,
                                    apiRepository: ApiRepository,
                                    view: DisplayFavoriteTeamAView,
                                    context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<DisplayFavoriteTeamAView>(gson, apiRepository, view, context) {

    fun setActionBar(resources: Resources) {
        view.displayActionBarTitle(resources.getString(R.string.my_team_favorites))
    }

    fun loadFavoriteTeam(database: SQLiteHelper) {
        try {
            database.use {
                val q = select(FavoriteTeam.TABLE_FAVORITETEAM)
                val listTeams = q.parseList(classParser<FavoriteTeam>())
                view.showFavoriteTeams(listTeams)
            }
        } catch (e: SQLiteConstraintException) {
            view.showError(e.message)
        }
    }
}