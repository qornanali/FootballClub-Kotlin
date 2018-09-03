package com.qornanali.footballclubkt.feature

open class BasePresenter<V : BaseView>() {

    lateinit var view: V

    fun attachView(baseView: V) {
        view = baseView
    }

}