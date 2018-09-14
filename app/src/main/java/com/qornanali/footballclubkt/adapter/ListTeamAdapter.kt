package com.qornanali.footballclubkt.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.holder.ItemTeamHolder
import com.qornanali.footballclubkt.model.Team
import com.qornanali.footballclubkt.util.OnItemClickListener

class ListTeamAdapter(val list: ArrayList<Team> = ArrayList(), val onItemClickListener: OnItemClickListener<Team>? = null) : RecyclerView.Adapter<ItemTeamHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTeamHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_team, parent, false)
        return ItemTeamHolder(view)
    }

    override fun onBindViewHolder(holder: ItemTeamHolder, position: Int) {
        holder.bind(list.get(position))
        holder.itemView.setOnClickListener { it ->
            onItemClickListener?.onClicked(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}