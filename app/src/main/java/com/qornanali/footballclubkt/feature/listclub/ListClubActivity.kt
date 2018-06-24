package com.qornanali.footballclubkt.feature.listclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.qornanali.footballclub_kotlin.R.array.club_image
import com.qornanali.footballclub_kotlin.R.array.club_name
import com.qornanali.footballclubkt.adapter.ListItemClubAdapter
import com.qornanali.footballclubkt.adapter.OnItemClickListener
import com.qornanali.footballclubkt.feature.detailclub.DetailClubActivity
import com.qornanali.footballclubkt.model.ItemClub
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity


class ListClubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = resources.getStringArray(club_name)
        val image = resources.obtainTypedArray(club_image)

        var listClub = ArrayList<ItemClub>()
        for (i in name.indices) {
            listClub.add(ItemClub(name[i], image.getResourceId(i, 0)))
        }

        val listItemClubAdapter = ListItemClubAdapter(listClub,
                onItemClickListener = OnItemClickListener<ItemClub> {
                    startActivity<DetailClubActivity>("club" to it)
                }
        )
        ListClubUI(listItemClubAdapter).setContentView(this)
    }
}
