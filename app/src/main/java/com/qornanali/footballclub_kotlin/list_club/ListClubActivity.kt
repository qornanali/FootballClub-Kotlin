package com.qornanali.footballclub_kotlin.list_club

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclub_kotlin.adapter.ListItemClubAdapter
import com.qornanali.footballclub_kotlin.model.ItemClub
import org.jetbrains.anko.setContentView

class ListClubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)

        var listClub = ArrayList<ItemClub>()
        for (i in name.indices) {
            listClub.add(ItemClub(name[i], image.getResourceId(i, 0)))
        }

        val listItemClubAdapter = ListItemClubAdapter(this, listClub)
        ListClubUI(listItemClubAdapter).setContentView(this)
    }
}
