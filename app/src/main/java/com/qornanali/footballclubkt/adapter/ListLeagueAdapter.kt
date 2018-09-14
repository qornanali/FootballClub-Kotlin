package com.qornanali.footballclubkt.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.holder.ItemLeagueHolder
import com.qornanali.footballclubkt.model.League
import com.qornanali.footballclubkt.util.OnItemClickListener

class ListLeagueAdapter(val list: ArrayList<League> = ArrayList(), val onItemClickListener: OnItemClickListener<League>? = null) : RecyclerView.Adapter<ItemLeagueHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemLeagueHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_league, parent, false)
        return ItemLeagueHolder(view)
    }

    override fun onBindViewHolder(holder: ItemLeagueHolder, position: Int) {
        holder.bind(list.get(position))
        holder.itemView.setOnClickListener { it ->
            onItemClickListener?.onClicked(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}