package com.qornanali.footballclubkt.model

import com.google.gson.annotations.SerializedName

data class ResponseGetLeague(
        @SerializedName("countrys") val leagues: List<League>
)