package com.qornanali.footballclubkt.model

import com.google.gson.annotations.SerializedName

data class ResponseGetPlayers(
        @SerializedName("players") val players: List<Player>
)