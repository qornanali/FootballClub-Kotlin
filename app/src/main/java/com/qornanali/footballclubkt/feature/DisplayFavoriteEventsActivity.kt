package com.qornanali.footballclubkt.feature

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.qornanali.footballclub_kotlin.R
import org.jetbrains.anko.find


class DisplayFavoriteEventsActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displayfavoriteevents)

        toolbar = find(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.my_favorites)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
