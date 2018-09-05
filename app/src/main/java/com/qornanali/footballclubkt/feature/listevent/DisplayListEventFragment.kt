package com.qornanali.footballclubkt.feature.listevent

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.ListEventAdapter
import com.qornanali.footballclubkt.feature.BaseFragment
import com.qornanali.footballclubkt.feature.detailevent.DisplayDetailEventActivity
import com.qornanali.footballclubkt.model.Event
import com.qornanali.footballclubkt.util.OnItemClickListener
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class DisplayListEventFragment : BaseFragment<DisplayListEventFPresenter, DisplayListEventFView>(), DisplayListEventFView {

    private lateinit var rvEvents: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: ListEventAdapter
    private val events = ArrayList<Event>()

    override fun attachPresenter(): DisplayListEventFPresenter {
        return DisplayListEventFPresenter()
    }

    override fun showListEvent(data: List<Event>) {
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onInitializeViews() {
        rvEvents = rootView.find(R.id.rv_events)
        progressBar = rootView.find(R.id.progress_bar)
        adapter = ListEventAdapter(events, OnItemClickListener {
            activity?.startActivity<DisplayDetailEventActivity>("event" to it)
        })

        rvEvents.layoutManager = LinearLayoutManager(activity)
        rvEvents.adapter = adapter

        presenter.attachView(this)

        presenter.loadListEvent(arguments?.getString("title"), resources.getString(R.string.last_events))
    }

    override fun attachLayout(): Int {
        return R.layout.fragment_displaylistevents
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
