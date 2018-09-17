package com.qornanali.footballclubkt.feature.listevent

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.ListEventAdapter
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.feature.BaseFragment
import com.qornanali.footballclubkt.feature.detailevent.DisplayDetailEventActivity
import com.qornanali.footballclubkt.feature.favoritevent.DisplayFavoriteEventsActivity
import com.qornanali.footballclubkt.feature.home.HomeActivity
import com.qornanali.footballclubkt.feature.searchevent.SearchEventsActivity
import com.qornanali.footballclubkt.model.Event
import com.qornanali.footballclubkt.util.OnItemClickListener
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class DisplayListEventFragment :
        BaseFragment<DisplayListEventFPresenter, DisplayListEventFView>(),
        DisplayListEventFView {

    private lateinit var rvEvents: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: ListEventAdapter
    private val events = ArrayList<Event>()

    override fun attachPresenter(): DisplayListEventFPresenter {
        return DisplayListEventFPresenter(Gson(), ApiRepository(), this)
    }

    override fun showListEvent(data: List<Event>) {
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_events, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.m_action_favorites -> activity?.startActivity<DisplayFavoriteEventsActivity>()
            R.id.m_action_search -> activity?.startActivity<SearchEventsActivity>()
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    override fun onInitializeViews() {
        rvEvents = if (arguments?.getString("title").equals(resources.getString(R.string.next_events))) rootView.find(R.id.rv_next_events) else rootView.find(R.id.rv_last_events)
        progressBar = rootView.find(R.id.progress_bar)
        adapter = ListEventAdapter(events, OnItemClickListener {
            activity?.startActivity<DisplayDetailEventActivity>("event" to it)
        })

        rvEvents.layoutManager = LinearLayoutManager(activity)
        rvEvents.adapter = adapter

        presenter.loadListEvent(arguments?.getString("title"), resources.getString(R.string.last_events),(activity as HomeActivity).league?.idLeague)
    }

    override fun attachLayout(): Int {
        return if (arguments?.getString("title").equals(resources.getString(R.string.next_events))) R.layout.fragment_displaynextevents else R.layout.fragment_displaylastevents
    }

    override fun loadingData(status: Boolean) {
        if (status) {
            progressBar.visibility = View.VISIBLE
            rvEvents.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            rvEvents.visibility = View.VISIBLE
        }
    }
}
