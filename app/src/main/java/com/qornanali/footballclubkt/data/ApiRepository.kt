package com.qornanali.footballclubkt.data

import android.util.Log
import java.net.URL

class ApiRepository{

    fun doRequest(url: String): String{
        return URL(url).readText()
    }
}