package com.qornanali.footballclubkt.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.qornanali.footballclubkt.model.FavoriteEvent
import com.qornanali.footballclubkt.model.FavoriteTeam
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
                FavoriteEvent.FIELD_AWAYID to TEXT
        )
        db.createTable(FavoriteTeam.TABLE_FAVORITETEAM, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.FIELD_IDTEAM to TEXT + UNIQUE,
                FavoriteTeam.FIELD_NAME to TEXT,
                FavoriteTeam.FIELD_SHORTNAME to TEXT,
                FavoriteTeam.FIELD_ALTERNATE to TEXT,
                FavoriteTeam.FIELD_FORMEDYEAR to TEXT,
                FavoriteTeam.FIELD_MANAGER to TEXT,
                FavoriteTeam.FIELD_STADIUM to TEXT,
                FavoriteTeam.FIELD_STADIUMTHUMB to TEXT,
                FavoriteTeam.FIELD_STADIUMDESC to TEXT,
                FavoriteTeam.FIELD_STADIUMLOCATION to TEXT,
                FavoriteTeam.FIELD_STADIUMCAPACITY to TEXT,
                FavoriteTeam.FIELD_STADIUMCAPACITY to TEXT,
                FavoriteTeam.FIELD_DESCRIPTION to TEXT,
                FavoriteTeam.FIELD_COUNTRY to TEXT,
                FavoriteTeam.FIELD_BADGE to TEXT,
                FavoriteTeam.FIELD_JERSEY to TEXT,
                FavoriteTeam.FIELD_LOGO to TEXT,
                FavoriteTeam.FIELD_FANART1 to TEXT,
                FavoriteTeam.FIELD_FANART2 to TEXT,
                FavoriteTeam.FIELD_FANART3 to TEXT,
                FavoriteTeam.FIELD_FANART4 to TEXT,
                FavoriteTeam.FIELD_BANNER to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteEvent.TABLE_FAVORITEEVENT, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITETEAM, true)
    }
}

val Context.database: SQLiteHelper
    get() = SQLiteHelper.getInstance(applicationContext)