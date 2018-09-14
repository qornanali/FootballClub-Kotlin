package com.qornanali.footballclubkt.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.model.Team
import com.squareup.picasso.Picasso

class ItemTeamHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val ivTeamBadge: ImageView = itemView.findViewById(R.id.iv_team_badge)
    private val tvTeamName: TextView = itemView.findViewById(R.id.tv_team_name)

    fun bind(team: Team) {
        tvTeamName.text = team.strTeam
        team.strTeamBadge?.let {
            Picasso.get().load(team.strTeamBadge).resize(80, 100)
                    .error(R.color.colorGray).into(ivTeamBadge)
        }
    }
}
