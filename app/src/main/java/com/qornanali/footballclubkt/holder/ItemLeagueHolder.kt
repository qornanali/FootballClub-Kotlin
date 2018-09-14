package com.qornanali.footballclubkt.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.model.League

class ItemLeagueHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvLeagueName: TextView = itemView.findViewById(R.id.tv_league_name)

    fun bind(league: League) {
        tvLeagueName.text = league.strLeague
    }
}
