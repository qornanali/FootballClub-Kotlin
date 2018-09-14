package com.qornanali.footballclubkt.model

import com.google.gson.annotations.SerializedName

data class ResponseGetLeague(
        @SerializedName("leagues") val leagues: List<League>
)