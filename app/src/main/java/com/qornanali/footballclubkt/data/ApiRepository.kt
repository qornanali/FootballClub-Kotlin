package com.qornanali.footballclubkt.data

import android.util.Log
import java.net.URL

class ApiRepository{

    fun doRequest(url: String): String{
        Log.i("wew", "Get " + url)
        return URL(url).readText()
    }
}