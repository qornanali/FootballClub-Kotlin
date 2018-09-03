package com.qornanali.footballclubkt.model

import com.google.gson.annotations.SerializedName

data class ResponseGetEvents(
        @SerializedName("events") val events: List<Event>
)