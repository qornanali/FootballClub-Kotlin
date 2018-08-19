package com.qornanali.footballclubkt.feature

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TsdbAPI
import com.qornanali.footballclubkt.data.database
import com.qornanali.footballclubkt.data.model.Event
import com.qornanali.footballclubkt.data.model.FavoriteEvent
import com.qornanali.footballclubkt.data.model.ResponseGetTeams
import com.qornanali.footballclubkt.data.model.Statistic
import com.qornanali.footballclubkt.util.DateFormatter
import com.qornanali.footballclubkt.util.adapter.ListStatisticAdapter
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread


class DisplayDetailEventActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tvEventDate: TextView
    private lateinit var ivTeamHomeLogo: ImageView
    private lateinit var ivTeamAwayLogo: ImageView
    private lateinit var tvEventHomeTeam: TextView
    private lateinit var tvEventAwayTeam: TextView
    private lateinit var tvEventHomeScore: TextView
    private lateinit var tvEventAwayScore: TextView
    private lateinit var event: Event
    private lateinit var homeBadge: String
    private lateinit var awayBadge: String
    private lateinit var rvStatistics: RecyclerView
    private lateinit var adapter: ListStatisticAdapter
    private var statistics = ArrayList<Statistic>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displaydetailevent)

        event = intent.extras.getSerializable("event") as Event

        toolbar = find(R.id.toolbar)
        tvEventDate = find(R.id.tv_event_date)
        ivTeamHomeLogo = find(R.id.iv_team_homelogo)
        ivTeamAwayLogo = find(R.id.iv_team_awaylogo)
        tvEventHomeTeam = find(R.id.tv_event_hometeam)
        tvEventAwayTeam = find(R.id.tv_event_awayteam)
        tvEventHomeScore = find(R.id.tv_event_homescore)
        tvEventAwayScore = find(R.id.tv_event_awayscore)
        rvStatistics = find(R.id.rv_statistics)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.detail_event)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        adapter = ListStatisticAdapter(statistics)

        rvStatistics.layoutManager = LinearLayoutManager(this)
        rvStatistics.adapter = adapter

        val eventDate = DateFormatter.toDate("yyyy/MM/dd hh:mm", event.strDate + " " + event.strTime.split("+").get(0))
        tvEventDate.text = DateFormatter.toString(eventDate!!, "dddd, MMMM yyyy hh:mm")
        tvEventAwayTeam.text = event.strAwayTeam
        tvEventHomeTeam.text = event.strHomeTeam

        val gson = Gson()
        val apiRepository = ApiRepository()

        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TsdbAPI.getTeamDetail(event.idAwayTeam)), ResponseGetTeams::class.java)
            uiThread {
                awayBadge = data.teams.get(0).strTeamBadge
                Picasso.get().load(awayBadge).resize(150, 150).into(ivTeamAwayLogo)
            }
        }
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TsdbAPI.getTeamDetail(event.idHomeTeam)), ResponseGetTeams::class.java)
            uiThread {
                homeBadge = data.teams.get(0).strTeamBadge
                Picasso.get().load(homeBadge).resize(150, 150).into(ivTeamHomeLogo)
            }
        }

        tvEventAwayScore.text = event.intAwayScore
        tvEventHomeScore.text = event.intHomeScore

        statistics.add(Statistic(event.strHomeFormation, resources.getString(R.string.formation), event.strAwayFormation))
        statistics.add(Statistic(event.intHomeShots, resources.getString(R.string.shots), event.intAwayShots))
        statistics.add(Statistic(event.strHomeRedCards, resources.getString(R.string.red_cards), event.strAwayRedCards))
        statistics.add(Statistic(event.strHomeYellowCards, resources.getString(R.string.yellow_cards), event.strAwayYellowCards))
        statistics.add(Statistic(event.strHomeLineupForward, resources.getString(R.string.forward), event.strAwayLineupForward))
        statistics.add(Statistic(event.strHomeLineupMidfield, resources.getString(R.string.midfielder), event.strAwayLineupMidfield))
        statistics.add(Statistic(event.strHomeLineupDefense, resources.getString(R.string.defender), event.strAwayLineupDefense))
        statistics.add(Statistic(event.strHomeLineupGoalkeeper, resources.getString(R.string.goalkeeper), event.strAwayLineupGoalkeeper))
        statistics.add(Statistic(event.strHomeLineupSubstitutes, resources.getString(R.string.subtitutes), event.strAwayLineupSubstitutes))

        adapter.notifyDataSetChanged()

        changeFavoriteMenuIcon(checkFavorited())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorites, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.m_favorites -> {
                val statusFavorited = checkFavorited()
                if (statusFavorited) {
                    removeFromFavorite()
                } else {
                    addToFavorite()
                }
                changeFavoriteMenuIcon(statusFavorited)
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    private fun changeFavoriteMenuIcon(isFavorited: Boolean) {
        if (isFavorited) {

        } else {

        }
    }

    private fun addToFavorite() {
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
                        FavoriteEvent.FIELD_AWAYID to event.idAwayTeam,
                        FavoriteEvent.FIELD_HOMEBADGE to homeBadge,
                        FavoriteEvent.FIELD_AWAYBADGE to awayBadge)
            }
            Toast.makeText(this, resources.getString(R.string.added_to_favorites), Toast.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteEvent.TABLE_FAVORITEEVENT,
                        "({field} = {value})",
                        "field" to FavoriteEvent.FIELD_IDEVENT,
                        "value" to event.idEvent)
            }
            Toast.makeText(this, resources.getString(R.string.removed_from_favorites), Toast.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun checkFavorited(): Boolean {
        var favorited = false
        try {
            database.use {
                val q = select(FavoriteEvent.TABLE_FAVORITEEVENT, FavoriteEvent.FIELD_IDEVENT)
                        .whereArgs("({field} = {value})",
                                "field" to FavoriteEvent.FIELD_IDEVENT,
                                "value" to event.idEvent)
                val listFavorites = q.parseList(classParser<FavoriteEvent>())
                favorited = !listFavorites.isEmpty()
            }
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
        return favorited
    }
}
