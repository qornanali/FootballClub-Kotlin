package com.qornanali.footballclubkt.model

import com.google.gson.annotations.SerializedName

data class ResponseGetPlayers(
        @SerializedName("player") val players: List<Player>
)