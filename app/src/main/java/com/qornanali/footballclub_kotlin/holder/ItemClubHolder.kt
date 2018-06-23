package com.qornanali.footballclub_kotlin.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclub_kotlin.model.ItemClub
import com.squareup.picasso.Picasso

class ItemClubHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imgClubLogo: ImageView = itemView.findViewById(R.id.iv_club_logo) as ImageView
    val txtClubName: TextView = itemView.findViewById(R.id.tv_club_name) as TextView

    fun bind(itemClub: ItemClub) {
        Picasso.get().load(itemClub.logo).resize(72, 72).into(imgClubLogo)
        txtClubName.setText(itemClub.name)
    }
}
