package com.qornanali.footballclubkt.feature.favoritevent

import android.content.res.Resources
import android.database.sqlite.SQLiteConstraintException
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.SQLiteHelper
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.FavoriteEvent
import com.qornanali.footballclubkt.util.CoroutineContextProvider
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class DisplayFavoriteEventsAPresenter(gson: Gson,
                                      apiRepository: ApiRepository,
                                      view: DisplayFavoriteEventsAView,
                                      context: CoroutineContextProvider = CoroutineContextProvider())
    : BasePresenter<DisplayFavoriteEventsAView>(gson, apiRepository, view, context) {

    fun setActionBar(resources: Resources) {
        view.displayActionBarTitle(resources.getString(R.string.my_event_favorites))
    }

    fun loadFavoriteEvents(database: SQLiteHelper) {
        try {
            database.use {
                val q = select(FavoriteEvent.TABLE_FAVORITEEVENT)
                val listFavorites = q.parseList(classParser<FavoriteEvent>())
                view.showFavoriteEvents(listFavorites)
            }
        } catch (e: SQLiteConstraintException) {
            view.showError(e.message)
        }
    }
}