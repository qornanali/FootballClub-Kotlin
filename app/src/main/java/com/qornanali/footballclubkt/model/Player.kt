package com.qornanali.footballclubkt.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Player(
        @SerializedName("idPlayer") val idPlayer: String?,
        @SerializedName("strNationality") val strNationality: String?,
        @SerializedName("strPlayer") val strPlayer: String?,
        @SerializedName("strTeam") val strTeam: String?,
        @SerializedName("dateBorn") val dateBorn: String?,
        @SerializedName("dateSigned") val dateSigned: String?,
        @SerializedName("strSigning") val strSigning: String?,
        @SerializedName("strWage") val strWage: String?,
        @SerializedName("strBirthLocation") val strBirthLocation: String?,
        @SerializedName("strDescriptionEN") val strDescriptionEN: String?,
        @SerializedName("strGender") val strGender: String?,
        @SerializedName("strPosition") val strPosition: String?,
        @SerializedName("strHeight") val strHeight: String?,
        @SerializedName("strWeight") val strWeight: String?,
        @SerializedName("strThumb") val strThumb: String?,
        @SerializedName("strCutout") val strCutout: String?,
        @SerializedName("strBanner") val strBanner: String?,
        @SerializedName("strFanart1") val strFanart1: String?,
        @SerializedName("strFanart2") val strFanart2: String?,
        @SerializedName("strFanart3") val strFanart3: String?,
        @SerializedName("strFanart4") val strFanart4: String?
) : Serializable