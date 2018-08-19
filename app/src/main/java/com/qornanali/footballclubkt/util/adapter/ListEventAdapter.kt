package com.qornanali.footballclubkt.util.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.util.holder.ItemEventHolder
import com.qornanali.footballclubkt.data.model.Event
import com.qornanali.footballclubkt.util.OnItemClickListener

class ListEventAdapter(val list: ArrayList<Event> = ArrayList(), val onItemClickListener: OnItemClickListener<Event>) : RecyclerView.Adapter<ItemEventHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemEventHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event, parent, false)
        return ItemEventHolder(view)
    }

    override fun onBindViewHolder(holder: ItemEventHolder, position: Int) {
        holder.bind(list.get(position))
        holder.itemView.setOnClickListener { it ->
            onItemClickListener.onClicked(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}