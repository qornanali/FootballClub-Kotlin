package com.qornanali.footballclubkt.model

import com.google.gson.annotations.SerializedName

data class ResponseGetTeams(
        @SerializedName("teams") val teams: List<Team>
)