package com.qornanali.footballclubkt.feature.favoritevent

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.ListEventAdapter
import com.qornanali.footballclubkt.data.database
import com.qornanali.footballclubkt.feature.BaseActivity
import com.qornanali.footballclubkt.feature.detailevent.DisplayDetailEventActivity
import com.qornanali.footballclubkt.model.Event
import com.qornanali.footballclubkt.model.FavoriteEvent
import com.qornanali.footballclubkt.util.OnItemClickListener
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class DisplayFavoriteEventsActivity : BaseActivity<DisplayFavoriteEventsAPresenter, DisplayFavoriteEventsAView>(), DisplayFavoriteEventsAView {

    override fun showError(message: CharSequence?) {
        if (message != null) {
            toast(message)
        }
    }

    private lateinit var toolbar: Toolbar
    private lateinit var rvEvents: RecyclerView
    private lateinit var adapter: ListEventAdapter
    private var events = ArrayList<Event>()

    override fun showFavoriteEvents(data: List<FavoriteEvent>) {
        for (favorite in data) {
            events.add(Event(favorite))
        }
        adapter.notifyDataSetChanged()
    }

    override fun displayActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun attachLayout(): Int {
        return R.layout.activity_displayfavoriteevents
    }

    override fun onInitializeViews() {
        toolbar = find(R.id.toolbar)
        rvEvents = find(R.id.rv_events)

        setSupportActionBar(toolbar)

        adapter = ListEventAdapter(events, OnItemClickListener {
            startActivity<DisplayDetailEventActivity>("event" to it)
        })

        rvEvents.layoutManager = LinearLayoutManager(this)
        rvEvents.adapter = adapter

        presenter.attachView(this)

        presenter.setActionBar(resources)

        presenter.loadFavoriteEvents(database = database)
    }

    override fun attachPresenter(): DisplayFavoriteEventsAPresenter {
        return DisplayFavoriteEventsAPresenter()
    }
}
