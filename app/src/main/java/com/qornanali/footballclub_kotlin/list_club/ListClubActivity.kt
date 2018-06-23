package com.qornanali.footballclub_kotlin.list_club

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclub_kotlin.adapter.ListItemClubAdapter
import com.qornanali.footballclub_kotlin.adapter.OnItemClickListener
import com.qornanali.footballclub_kotlin.detail_club.DetailClubActivity
import com.qornanali.footballclub_kotlin.model.ItemClub
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity


class ListClubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)

        var listClub = ArrayList<ItemClub>()
        for (i in name.indices) {
            listClub.add(ItemClub(name[i], image.getResourceId(i, 0)))
        }

        val listItemClubAdapter = ListItemClubAdapter(this, listClub,
                onItemClickListener = OnItemClickListener<ItemClub> {
                    startActivity<DetailClubActivity>("club" to it)
                }
        )
        ListClubUI(listItemClubAdapter).setContentView(this)
    }
}
