package com.qornanali.footballclubkt.model

import com.google.gson.annotations.SerializedName

data class League(
        @SerializedName("idLeague") val idLeague: String?,
        @SerializedName("strLeague") val strLeague: String?
)