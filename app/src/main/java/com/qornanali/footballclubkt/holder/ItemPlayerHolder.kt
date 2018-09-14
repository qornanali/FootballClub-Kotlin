package com.qornanali.footballclubkt.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.model.League
import com.qornanali.footballclubkt.model.Player
import com.squareup.picasso.Picasso

class ItemPlayerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvPlayerName: TextView = itemView.findViewById(R.id.tv_player_name)
    private val ivPlayerImage: ImageView = itemView.findViewById(R.id.iv_player_image)

    fun bind(player: Player) {
        tvPlayerName.text = player.strPlayer
        Picasso.get().load(player.strThumb).resize(60, 60)
                .error(R.color.colorGray).into(ivPlayerImage)
    }
}
