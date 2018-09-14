package com.qornanali.footballclubkt.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.holder.ItemPlayerHolder
import com.qornanali.footballclubkt.model.Player
import com.qornanali.footballclubkt.util.OnItemClickListener

class ListPlayerAdapter(val list: ArrayList<Player> = ArrayList(), val onItemClickListener: OnItemClickListener<Player>? = null) : RecyclerView.Adapter<ItemPlayerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPlayerHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_player, parent, false)
        return ItemPlayerHolder(view)
    }

    override fun onBindViewHolder(holder: ItemPlayerHolder, position: Int) {
        holder.bind(list.get(position))
        holder.itemView.setOnClickListener { it ->
            onItemClickListener?.onClicked(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}