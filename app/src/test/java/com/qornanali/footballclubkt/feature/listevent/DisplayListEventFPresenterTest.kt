package com.qornanali.footballclubkt.feature.listevent

import com.google.gson.Gson
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TheSportdbAPI
import com.qornanali.footballclubkt.model.Event
import com.qornanali.footballclubkt.model.ResponseGetEvents
import com.qornanali.footballclubkt.util.TestContextProvider
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

class DisplayListEventFPresenterTest {

    @Mock
    private lateinit var view: DisplayListEventFView
    @Mock
    private lateinit var apiRepository: ApiRepository
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var presenter: DisplayListEventFPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DisplayListEventFPresenter(gson, apiRepository, view, TestContextProvider())
    }

    @Test
    fun loadListEvent() {
        val events = ArrayList<Event>()
        val event = ArrayList<Event>()
        val eventsTypeComparator = "Last matches"
        val title = "Last matches"
        val responseGetEvents = ResponseGetEvents(events, event)
        val idLeague = "4328"

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(TheSportdbAPI.getLastEventsByLeagueId(idLeague)), ResponseGetEvents::class.java))
                .thenReturn(responseGetEvents)

        presenter.loadListEvent(title, eventsTypeComparator, idLeague)

        Mockito.verify(view).loadingData(true)
        Mockito.verify(view).showListEvent(events)
        Mockito.verify(view).loadingData(false)
    }



}