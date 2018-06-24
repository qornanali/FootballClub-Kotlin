package com.qornanali.footballclubkt.feature.listclub

import android.support.v7.widget.LinearLayoutManager
import com.qornanali.footballclubkt.adapter.ListItemClubAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ListClubUI(val listItemClubAdapter: ListItemClubAdapter) : AnkoComponent<ListClubActivity> {

    override fun createView(ui: AnkoContext<ListClubActivity>) = with(ui) {
        verticalLayout {
//            toolbar {
//                id = R.id.toolbar
//            }.lparams(width = matchParent, height = wrapContent)
            relativeLayout {
                lparams(width = matchParent, height = wrapContent)
                recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                    adapter = listItemClubAdapter
                }
            }
        }
    }

}