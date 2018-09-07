package com.qornanali.footballclubkt.feature

import com.google.gson.Gson
import com.qornanali.footballclubkt.data.ApiRepository
import com.qornanali.footballclubkt.util.CoroutineContextProvider

open class BasePresenter<V : BaseView>(open val gson: Gson,
                                       open val apiRepository: ApiRepository,
                                       open val view: V,
                                       open val context: CoroutineContextProvider = CoroutineContextProvider())