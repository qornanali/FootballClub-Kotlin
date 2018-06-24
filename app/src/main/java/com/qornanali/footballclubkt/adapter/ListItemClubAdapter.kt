package com.qornanali.footballclubkt.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.qornanali.footballclubkt.holder.ItemClubHolder
import com.qornanali.footballclubkt.feature.listclub.ItemClubUI
import com.qornanali.footballclubkt.model.ItemClub
import org.jetbrains.anko.AnkoContext

class ListItemClubAdapter(val list: ArrayList<ItemClub> = ArrayList<ItemClub>(), val onItemClickListener: OnItemClickListener<ItemClub>) : RecyclerView.Adapter<ItemClubHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemClubHolder {
        return ItemClubHolder(ItemClubUI().createView(AnkoContext.create(parent!!.context)))
    }

    override fun onBindViewHolder(holder: ItemClubHolder, position: Int) {
        holder.bind(list.get(position))
        holder.itemView.setOnClickListener { it ->
            onItemClickListener.onClicked(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}