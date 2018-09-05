package com.qornanali.footballclubkt.feature.listevent

import com.google.gson.Gson
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.data.TheSportdbAPI
import com.qornanali.footballclubkt.model.Event
import com.qornanali.footballclubkt.model.ResponseGetEvents
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
    private lateinit var presenter: DisplayListEventFPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DisplayListEventFPresenter()
        presenter.attachView(view)
    }

    @Test
    fun loadListEvent() {
        val events = ArrayList<Event>()
        val eventsTypeComparator = "Last matches"
        val title = "Last matches"
        val responseGetEvents = ResponseGetEvents(events)
        val idLeague = "4328"

        Mockito.`when`(Gson().fromJson(ApiRepository().doRequest(TheSportdbAPI.getLastEvents(idLeague)), ResponseGetEvents::class.java))
                .thenReturn(responseGetEvents)

        presenter.loadListEvent(title, eventsTypeComparator)

        Mockito.verify(view).showListEvent(events)
    }
}