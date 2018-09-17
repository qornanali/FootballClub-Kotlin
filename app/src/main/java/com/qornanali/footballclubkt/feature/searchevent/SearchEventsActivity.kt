package com.qornanali.footballclubkt.feature.searchevent

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.ListEventAdapter
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.feature.BaseActivity
import com.qornanali.footballclubkt.feature.detailevent.DisplayDetailEventActivity
import com.qornanali.footballclubkt.model.Event
import com.qornanali.footballclubkt.util.OnItemClickListener
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


class SearchEventsActivity :
        BaseActivity<SearchEventsAPresenter, SearchEventsAView>(),
        SearchEventsAView {

    private lateinit var rvSearchItems: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var etQuery: EditText
    private lateinit var adapter: ListEventAdapter
    private val events = ArrayList<Event>()

    override fun attachLayout(): Int {
        return R.layout.activity_search
    }

    override fun onInitializeViews() {
        rvSearchItems = find(R.id.rv_search_items)
        progressBar = find(R.id.progress_bar)
        etQuery = find(R.id.et_query)

        adapter = ListEventAdapter(events, OnItemClickListener {
            startActivity<DisplayDetailEventActivity>("event" to it)
        })

        rvSearchItems.layoutManager = LinearLayoutManager(this)
        rvSearchItems.adapter = adapter

        etQuery.hint = resources.getString(R.string.search_event_hint)
        etQuery.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                if(editable.toString().length > 4) presenter.searchEvent(editable.toString())
            }

            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    override fun attachPresenter(): SearchEventsAPresenter {
        return SearchEventsAPresenter(Gson(), ApiRepository(), this)
    }

    fun onIvBackClicked(v: View) {
        finish()
    }


    override fun loadingData(status: Boolean) {
        if (status) {
            progressBar.visibility = View.VISIBLE
            rvSearchItems.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            rvSearchItems.visibility = View.VISIBLE
        }
    }

    override fun showEvents(data: List<Event>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
