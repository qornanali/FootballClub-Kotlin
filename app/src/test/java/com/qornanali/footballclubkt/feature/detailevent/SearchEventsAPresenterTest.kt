package com.qornanali.footballclubkt.feature.detailevent

import com.google.gson.Gson
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.SQLiteHelper
import com.qornanali.footballclubkt.util.TestContextProvider
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchEventsAPresenterTest {

    @Mock
    private lateinit var view: DisplayDetailEventAView
    @Mock
    private lateinit var presenter: DisplayDetailEventAPresenter
    @Mock
    private lateinit var apiRepository: ApiRepository
    @Mock
    private lateinit var gson: Gson

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DisplayDetailEventAPresenter(gson, apiRepository, view, TestContextProvider())
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

        Mockito.verify(view).showTeamName(awayName, homeName)
    }



}