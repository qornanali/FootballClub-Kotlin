package com.qornanali.footballclubkt.feature.favoritevent

import android.content.res.Resources
import android.database.sqlite.SQLiteConstraintException
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.SQLiteHelper
import com.qornanali.footballclubkt.feature.BasePresenter
import com.qornanali.footballclubkt.model.FavoriteEvent
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class DisplayFavoriteEventsAPresenter : BasePresenter<DisplayFavoriteEventsAView>() {

    fun setActionBar(resources: Resources) {
        view.displayActionBarTitle(resources.getString(R.string.my_favorites))
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