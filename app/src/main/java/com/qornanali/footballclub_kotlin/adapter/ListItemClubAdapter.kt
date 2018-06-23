package com.qornanali.footballclub_kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.qornanali.footballclub_kotlin.detail_club.DetailClubActivity
import com.qornanali.footballclub_kotlin.holder.ItemClubHolder
import com.qornanali.footballclub_kotlin.list_club.ItemClubUI
import com.qornanali.footballclub_kotlin.model.ItemClub
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.startActivity

class ListItemClubAdapter(val context: Context, val list: ArrayList<ItemClub> = ArrayList<ItemClub>()) : RecyclerView.Adapter<ItemClubHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemClubHolder {
        return ItemClubHolder(ItemClubUI().createView(AnkoContext.create(parent!!.context)))
    }

    override fun onBindViewHolder(holder: ItemClubHolder, position: Int) {
        holder.bind(list.get(position))
        holder.itemView.setOnClickListener({ it ->
            context.startActivity<DetailClubActivity>("club" to list.get(position))
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun push(clubName: String, clubImage: Int) {
        insert(clubName, clubImage, 0)
    }

    fun insert(clubName: String, clubImage: Int, position: Int) {
        list.add(position, ItemClub(clubName, clubImage))
        notifyItemInserted(0)
    }

    fun pop() {
        remove(0)
    }

    fun remove(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(list.size)
    }

}