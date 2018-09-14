package com.qornanali.footballclubkt.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class League(
        @SerializedName("idLeague") val idLeague: String?,
        @SerializedName("strLeague") val strLeague: String?
) : Serializable