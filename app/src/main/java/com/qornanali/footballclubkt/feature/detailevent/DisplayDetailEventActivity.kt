package com.qornanali.footballclubkt.feature.detailevent

import android.database.sqlite.SQLiteConstraintException
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.ListStatisticAdapter
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.database
import com.qornanali.footballclubkt.feature.BaseActivity
import com.qornanali.footballclubkt.model.Event
import com.qornanali.footballclubkt.model.FavoriteEvent
import com.qornanali.footballclubkt.model.Statistic
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.toast


class DisplayDetailEventActivity :
        BaseActivity<DisplayDetailEventAPresenter, DisplayDetailEventAView>(),
        DisplayDetailEventAView {

    private lateinit var toolbar: Toolbar
    private lateinit var tvEventDate: TextView
    private lateinit var ivTeamHomeLogo: ImageView
    private lateinit var ivTeamAwayLogo: ImageView
    private lateinit var tvEventHomeTeam: TextView
    private lateinit var tvEventAwayTeam: TextView
    private lateinit var tvEventHomeScore: TextView
    private lateinit var tvEventAwayScore: TextView
    private lateinit var rvStatistics: RecyclerView
    private var event: Event? = null
    private lateinit var adapter: ListStatisticAdapter
    private var favoriteMenu: MenuItem? = null
    private val statistics = ArrayList<Statistic>()

    override fun attachLayout(): Int {
        return R.layout.activity_displaydetailevent
    }

    override fun onInitializeViews() {
        event = intent.extras.getSerializable("event") as Event

        toolbar = find(R.id.toolbar)
        tvEventDate = find(R.id.tv_event_date)
        ivTeamHomeLogo = find(R.id.iv_team_homelogo)
        ivTeamAwayLogo = find(R.id.iv_team_awaylogo)
        tvEventHomeTeam = find(R.id.tv_event_hometeam)
        tvEventAwayTeam = find(R.id.tv_event_awayteam)
        tvEventHomeScore = find(R.id.tv_event_homescore)
        tvEventAwayScore = find(R.id.tv_event_awayscore)
        rvStatistics = find(R.id.rv_statistics)

        adapter = ListStatisticAdapter(statistics)
        rvStatistics.layoutManager = LinearLayoutManager(this)
        rvStatistics.adapter = adapter
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter.setActionBar(resources)
        presenter.loadTeamsName(event?.strAwayTeam, event?.strHomeTeam)
        presenter.loadEventScore(event?.intHomeScore, event?.intAwayScore)
        presenter.loadAwayBadge(event?.idAwayTeam)
        presenter.loadHomeBadge(event?.idHomeTeam)
        presenter.loadStatistic(event, resources)
        presenter.loadEventDate(event?.strDate, event?.strTime)
    }

    override fun attachPresenter(): DisplayDetailEventAPresenter {
        return DisplayDetailEventAPresenter(Gson(), ApiRepository(), this)
    }

    override fun showError(message: CharSequence?) {
        if (message != null) {
            toast(message)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_event, menu)
        favoriteMenu = menu?.getItem(0)
        if (checkFavorited()) {
            setMenuItemIcon(favoriteMenu, R.drawable.ic_action_star_2)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.m_action_favorites -> {
                val statusFavorited = checkFavorited()
                if (statusFavorited) {
                    presenter.removeFromFavorite(event?.idEvent, database)
                } else {
                    presenter.addToFavorite(event, database)
                }
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }


    private fun loadImage(url: String?, ivTarget: ImageView) {
        Picasso.get().load(url).resize(150, 150).into(ivTarget)
    }

    private fun setMenuItemIcon(menuItem: MenuItem?, drawable: Int) {
        menuItem?.icon = ContextCompat.getDrawable(this, drawable)
    }

    private fun checkFavorited(): Boolean {
        var favorited = false
        try {
            database.use {
                val q = select(FavoriteEvent.TABLE_FAVORITEEVENT, FavoriteEvent.FIELD_IDEVENT)
                        .whereArgs("(FIELD_IDEVENT = {value})",
                                "value" to (event?.idEvent ?: ""))
                val listFavorites = q.parseList(classParser<String>())
                favorited = !listFavorites.isEmpty()
            }
        } catch (e: SQLiteConstraintException) {
            showError(e.message)
        }
        return favorited
    }

    override fun showTeamName(awayName: String?, homeName: String?) {
        tvEventAwayTeam.text = awayName
        tvEventHomeTeam.text = homeName
    }

    override fun showEventDate(date: String?) {
        tvEventDate.text = date
    }

    override fun displayActionBarTitle(title: String?) {
        supportActionBar?.title = resources.getString(R.string.detail_event)
    }

    override fun successRemovedFromFavorite() {
        toast(resources.getString(R.string.removed_from_favorites))
        setMenuItemIcon(favoriteMenu, R.drawable.ic_action_star)
    }

    override fun successAddedToFavorite() {
        toast(resources.getString(R.string.added_to_favorites))
        setMenuItemIcon(favoriteMenu, R.drawable.ic_action_star_2)
    }

    override fun showScores(awayScore: String?, homeScore: String?) {
        tvEventAwayScore.text = awayScore
        tvEventHomeScore.text = homeScore
    }

    override fun showStatistic(data: List<Statistic>) {
        statistics.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showAwayBadge(imageUrl: String?) {
        loadImage(imageUrl, ivTeamAwayLogo)
    }

    override fun showHomeBadge(imageUrl: String?) {
        loadImage(imageUrl, ivTeamHomeLogo)
    }
}
