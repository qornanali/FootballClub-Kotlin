package com.qornanali.footballclubkt.model

import com.google.gson.annotations.SerializedName

data class Schedule(
        @SerializedName("events") val events: List<Match>
)