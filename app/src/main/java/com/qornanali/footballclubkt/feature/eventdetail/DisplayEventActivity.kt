package com.qornanali.footballclubkt.feature.eventdetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.ListStatisticAdapter
import com.qornanali.footballclubkt.adapter.OnItemClickListener
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TsdbAPI
import com.qornanali.footballclubkt.data.model.Event
import com.qornanali.footballclubkt.data.model.ResponseGetTeams
import com.qornanali.footballclubkt.data.model.Statistic
import com.squareup.picasso.Picasso
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread


class DisplayEventActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tvEventDate: TextView
    private lateinit var ivTeamHomeLogo: ImageView
    private lateinit var ivTeamAwayLogo: ImageView
    private lateinit var tvEventHomeTeam: TextView
    private lateinit var tvEventAwayTeam: TextView
    private lateinit var tvEventHomeScore: TextView
    private lateinit var tvEventAwayScore: TextView
    private lateinit var event: Event

    private lateinit var rvStatistics: RecyclerView
    private lateinit var adapter: ListStatisticAdapter
    var statistics = ArrayList<Statistic>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displayevent)

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
        adapter = ListStatisticAdapter(statistics, OnItemClickListener {
            //nope here
        })

        rvStatistics.layoutManager = LinearLayoutManager(this)
        rvStatistics.adapter = adapter

        setSupportActionBar(toolbar)

        supportActionBar?.title = resources.getString(R.string.detail_event)

        if (event != null) {
            tvEventDate.text = event.strDate + " " + event.strTime.split("+").get(0)
            tvEventAwayTeam.text = event.strAwayTeam
            tvEventHomeTeam.text = event.strHomeTeam

            val gson = Gson()
            val apiRepository = ApiRepository()

            doAsync {
                val data = gson.fromJson(apiRepository
                        .doRequest(TsdbAPI.getTeamDetail(event.idAwayTeam)), ResponseGetTeams::class.java)
                uiThread {
                    val url = data.teams.get(0).strTeamBadge
                    Picasso.get().load(url).resize(150, 150).into(ivTeamAwayLogo)
                }
            }
            doAsync {
                val data = gson.fromJson(apiRepository
                        .doRequest(TsdbAPI.getTeamDetail(event.idHomeTeam)), ResponseGetTeams::class.java)
                uiThread {
                    val url = data.teams.get(0).strTeamBadge
                    Picasso.get().load(url).resize(150, 150).into(ivTeamHomeLogo)
                }
            }

            if (event.intAwayScore != null && event.intHomeScore != null) {
                tvEventAwayScore.text = event.intAwayScore
                tvEventHomeScore.text = event.intHomeScore

                statistics.add(Statistic(event.strHomeRedCards, resources.getString(R.string.red_cards), event.strAwayRedCards))
                statistics.add(Statistic(event.strHomeYellowCards, resources.getString(R.string.yellow_cards), event.strAwayYellowCards))
                statistics.add(Statistic(event.strHomeFormation, resources.getString(R.string.formation), event.strAwayFormation))
                statistics.add(Statistic(event.strHomeLineupForward, resources.getString(R.string.forward), event.strAwayLineupForward))
                statistics.add(Statistic(event.strHomeLineupMidfield, resources.getString(R.string.midfielder), event.strAwayLineupMidfield))
                statistics.add(Statistic(event.strHomeLineupDefense, resources.getString(R.string.defender), event.strAwayLineupDefense))
                statistics.add(Statistic(event.strHomeLineupGoalkeeper, resources.getString(R.string.goalkeeper), event.strAwayLineupGoalkeeper))
                statistics.add(Statistic(event.strHomeLineupSubstitutes, resources.getString(R.string.subtitutes), event.strAwayLineupSubstitutes))

                adapter.notifyDataSetChanged()
            }
        }

    }
}
