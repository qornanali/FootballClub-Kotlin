package com.qornanali.footballclubkt.feature

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Toast
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.data.database
import com.qornanali.footballclubkt.data.model.Event
import com.qornanali.footballclubkt.data.model.FavoriteEvent
import com.qornanali.footballclubkt.util.OnItemClickListener
import com.qornanali.footballclubkt.util.adapter.ListEventAdapter
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


class DisplayFavoriteEventsActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var rvEvents: RecyclerView
    private lateinit var adapter: ListEventAdapter
    private var events = ArrayList<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displayfavoriteevents)

        toolbar = find(R.id.toolbar)
        rvEvents = find(R.id.rv_events)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.my_favorites)

        adapter = ListEventAdapter(events, OnItemClickListener {
            startActivity<DisplayDetailEventActivity>("event" to it)
        })

        rvEvents.layoutManager = LinearLayoutManager(this)
        rvEvents.adapter = adapter
        try {
            database.use {
                val q = select(FavoriteEvent.TABLE_FAVORITEEVENT)
                val listFavorites = q.parseList(classParser<FavoriteEvent>())
                for (favorite in listFavorites) {
                    events.add(Event(favorite))
                }
                adapter.notifyDataSetChanged()
            }
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }
}
