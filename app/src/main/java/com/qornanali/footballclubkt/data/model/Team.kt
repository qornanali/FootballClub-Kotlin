package com.qornanali.footballclubkt.data.model

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("idTeam") val idTeam: String,
        @SerializedName("strTeam") val strTeam: String,
        @SerializedName("strTeamBadge") val strTeamBadge: String
)