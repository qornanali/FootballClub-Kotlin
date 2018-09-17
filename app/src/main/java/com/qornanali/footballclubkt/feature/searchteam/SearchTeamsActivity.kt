package com.qornanali.footballclubkt.feature.searchteam

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.ListTeamAdapter
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.feature.BaseActivity
import com.qornanali.footballclubkt.feature.detailteam.DisplayDetailTeamActivity
import com.qornanali.footballclubkt.model.Team
import com.qornanali.footballclubkt.util.OnItemClickListener
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


class SearchTeamsActivity :
        BaseActivity<SearchTeamsAPresenter, SearchTeamsAView>(),
        SearchTeamsAView {

    private lateinit var rvSearchItems: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var etQuery: EditText
    private lateinit var adapter: ListTeamAdapter
    private val teams = ArrayList<Team>()

    override fun attachLayout(): Int {
        return R.layout.activity_search
    }

    override fun onInitializeViews() {
        rvSearchItems = find(R.id.rv_search_items)
        progressBar = find(R.id.progress_bar)
        etQuery = find(R.id.et_query)

        adapter = ListTeamAdapter(teams, OnItemClickListener {
            startActivity<DisplayDetailTeamActivity>("team" to it)
        })

        rvSearchItems.layoutManager =  GridLayoutManager(this, 2)
        rvSearchItems.adapter = adapter

        etQuery.hint = resources.getString(R.string.search_team_hint)
        etQuery.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                if(editable.toString().length > 4) presenter.searchTeam(editable.toString())
            }

            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    override fun attachPresenter(): SearchTeamsAPresenter {
        return SearchTeamsAPresenter(Gson(), ApiRepository(), this)
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

    override fun showTeams(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
