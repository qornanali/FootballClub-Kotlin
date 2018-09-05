package com.qornanali.footballclubkt.feature.detailevent

import com.google.gson.Gson
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TheSportdbAPI
import com.qornanali.footballclubkt.feature.BaseView
import com.qornanali.footballclubkt.model.ResponseGetTeams
import com.qornanali.footballclubkt.model.Team
import com.qornanali.footballclubkt.util.DateFormatter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.*

class DisplayDetailEventAPresenterTest {

    @Mock
    private lateinit var view: DisplayDetailEventAView
    @Mock
    private lateinit var presenter: DisplayDetailEventAPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DisplayDetailEventAPresenter()
        presenter.attachView(view)
    }

    @Test
    fun loadEventDate() {
        val strDate = "15/09/18"
        val strTime = "14:00:00+00:00"
        val eventDate = "Saturday, 15 September 2018 14:00"

        presenter.loadEventDate(strDate, strTime)

        Mockito.verify(view).showEventDate(eventDate)
    }

    @Test
    fun loadTeamsName() {
        val homeName = "Chelsea"
        val awayName = "Manchester United"

        presenter.loadTeamsName(awayName, homeName)

        Mockito.verify(view).showTeamName(homeName, awayName)
    }

    @Test
    fun loadHomeBadge() {
        val teams = ArrayList<Team>()
        val idHomeTeam = "133932"
        val responseGetTeams = ResponseGetTeams(teams)
        val badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/qtusqx1420753809.png"

        `when`(Gson().fromJson(ApiRepository().doRequest(TheSportdbAPI.getTeamDetail(idHomeTeam)), ResponseGetTeams::class.java))
                .thenReturn(responseGetTeams)

        presenter.loadHomeBadge(idHomeTeam)

        Mockito.verify(view).showHomeBadges(badgeUrl)

    }


    @Test
    fun loadAwayBadge() {
        val teams = ArrayList<Team>()
        val idAwayTeam = "133632"
        val responseGetTeams = ResponseGetTeams(teams)
        val badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/rytxyw1448813222.png"

        `when`(Gson().fromJson(ApiRepository().doRequest(TheSportdbAPI.getTeamDetail(idAwayTeam)), ResponseGetTeams::class.java))
                .thenReturn(responseGetTeams)

        presenter.loadAwayBadge(idAwayTeam)

        Mockito.verify(view).showHomeBadges(badgeUrl)
    }
}