package com.qornanali.footballclubkt.feature

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.util.adapter.ListEventAdapter
import com.qornanali.footballclubkt.util.OnItemClickListener
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TsdbAPI
import com.qornanali.footballclubkt.data.model.Event
import com.qornanali.footballclubkt.data.model.ResponseGetEvents
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class DisplayListEventsFragment : Fragment() {

    private lateinit var rvEvents: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: ListEventAdapter
    private var events = ArrayList<Event>()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.fragment_displaylistevents, container, false)

        rvEvents = rootView.find(R.id.rv_events)
        progressBar = rootView.find(R.id.progress_bar)

        adapter = ListEventAdapter(events, OnItemClickListener {
            activity?.startActivity<DisplayDetailEventActivity>("event" to it)
        })

        rvEvents.layoutManager = LinearLayoutManager(activity)
        rvEvents.adapter = adapter


        loadingData(true)
        arguments?.takeIf { it.containsKey("title") }?.apply {
            var url = if (getString("title").equals(resources.getString(R.string.last_events))) TsdbAPI.getLastEvents("4328") else TsdbAPI.getNextEvents("4328")
            val gson = Gson()
            val apiRepository = ApiRepository()
            doAsync {
                val data = gson.fromJson(apiRepository
                        .doRequest(url), ResponseGetEvents::class.java)
                uiThread {
                    events.addAll(data.events)
                    adapter.notifyDataSetChanged()
                    loadingData(false)
                }
            }
        }
        return rootView
    }

    fun loadingData(status: Boolean) {
        if (status) {
            progressBar.visibility = View.VISIBLE
            rvEvents.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            rvEvents.visibility = View.VISIBLE
        }
    }
}
