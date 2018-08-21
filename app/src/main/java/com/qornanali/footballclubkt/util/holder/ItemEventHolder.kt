package com.qornanali.footballclubkt.util.holder

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.model.Event
import com.qornanali.footballclubkt.util.DateFormatter

class ItemEventHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvEventDate: TextView = itemView.findViewById(R.id.tv_event_date)
    private val tvEventHomeScore: TextView = itemView.findViewById(R.id.tv_event_homescore)
    private val tvEventAwayScore: TextView = itemView.findViewById(R.id.tv_event_awayscore)
    private val tvEventHomeTeam: TextView = itemView.findViewById(R.id.tv_event_hometeam)
    private val tvEventAwayTeam: TextView = itemView.findViewById(R.id.tv_event_awayteam)

    fun bind(event: Event) {
        var eventDate = DateFormatter.toDate(event.strDate + " " + event.strTime?.split("+")?.get(0), "dd/MM/yy HH:mm:ss")
        tvEventDate.text = DateFormatter.toString(eventDate!!, "EEE, MMMM yyyy HH:mm")
        tvEventAwayScore.text = if (event.intAwayScore != null) event.intAwayScore else ""
        tvEventHomeScore.text = if (event.intHomeScore != null) event.intHomeScore else ""
        tvEventHomeTeam.text = event.strHomeTeam
        tvEventAwayTeam.text = event.strAwayTeam
    }
}
