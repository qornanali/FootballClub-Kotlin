package com.qornanali.footballclubkt.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.qornanali.footballclubkt.data.model.FavoriteEvent
import org.jetbrains.anko.db.*

class SQLiteHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "footballclubkt.db", null, 1) {
    companion object {
        private var instance: SQLiteHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): SQLiteHelper {
            if (instance == null) {
                instance = SQLiteHelper(ctx.applicationContext)
            }
            return instance as SQLiteHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteEvent.TABLE_FAVORITEEVENT, true,
                FavoriteEvent.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteEvent.FIELD_IDEVENT to TEXT + UNIQUE,
                FavoriteEvent.FIELD_HOMETEAM to TEXT,
                FavoriteEvent.FIELD_AWAYTEAM to TEXT,
                FavoriteEvent.FIELD_HOMESCORE to TEXT,
                FavoriteEvent.FIELD_AWAYSCORE to TEXT,
                FavoriteEvent.FIELD_HOMERED to TEXT,
                FavoriteEvent.FIELD_HOMEYELLOW to TEXT,
                FavoriteEvent.FIELD_HOMEGK to TEXT,
                FavoriteEvent.FIELD_HOMEDF to TEXT,
                FavoriteEvent.FIELD_HOMEMF to TEXT,
                FavoriteEvent.FIELD_HOMEFW to TEXT,
                FavoriteEvent.FIELD_HOMESUB to TEXT,
                FavoriteEvent.FIELD_HOMEFORMATION to TEXT,
                FavoriteEvent.FIELD_AWAYRED to TEXT,
                FavoriteEvent.FIELD_AWAYYELLOW to TEXT,
                FavoriteEvent.FIELD_AWAYGK to TEXT,
                FavoriteEvent.FIELD_AWAYDF to TEXT,
                FavoriteEvent.FIELD_AWAYMF to TEXT,
                FavoriteEvent.FIELD_AWAYFW to TEXT,
                FavoriteEvent.FIELD_AWAYSUB to TEXT,
                FavoriteEvent.FIELD_AWAYFORMATION to TEXT,
                FavoriteEvent.FIELD_HOMESHOTS to TEXT,
                FavoriteEvent.FIELD_AWAYSHOTS to TEXT,
                FavoriteEvent.FIELD_DATE to TEXT,
                FavoriteEvent.FIELD_TIME to TEXT,
                FavoriteEvent.FIELD_HOMEID to TEXT,
                FavoriteEvent.FIELD_AWAYID to TEXT,
                FavoriteEvent.FIELD_HOMEBADGE to TEXT,
                FavoriteEvent.FIELD_AWAYBADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteEvent.TABLE_FAVORITEEVENT, true)
    }
}

val Context.database: SQLiteHelper
    get() = SQLiteHelper.getInstance(applicationContext)