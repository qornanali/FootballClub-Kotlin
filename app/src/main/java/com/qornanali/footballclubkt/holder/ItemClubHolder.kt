package com.qornanali.footballclubkt.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.model.ItemClub
import com.squareup.picasso.Picasso

class ItemClubHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //private val imgClubLogo: ImageView = itemView.findViewById(R.id.iv_club_logo)
    //private val txtClubName: TextView = itemView.findViewById(R.id.tv_club_name)

    fun bind(itemClub: ItemClub) {
      //  Picasso.get().load(itemClub.logo)
       //         .resize(72, 72).into(imgClubLogo)
       // txtClubName.text = itemClub.name
    }
}
