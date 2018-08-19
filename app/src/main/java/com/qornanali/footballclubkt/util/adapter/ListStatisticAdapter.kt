package com.qornanali.footballclubkt.util.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.util.holder.ItemStatisticHolder
import com.qornanali.footballclubkt.data.model.Statistic
import com.qornanali.footballclubkt.util.OnItemClickListener

class ListStatisticAdapter(val list: ArrayList<Statistic> = ArrayList()) : RecyclerView.Adapter<ItemStatisticHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemStatisticHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_statistic, parent, false)
        return ItemStatisticHolder(view)
    }

    override fun onBindViewHolder(holder: ItemStatisticHolder, position: Int) {
        holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }
}