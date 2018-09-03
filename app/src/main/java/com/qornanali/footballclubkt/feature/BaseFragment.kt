package com.qornanali.footballclubkt.feature

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment<P : BasePresenter<V>, V : BaseView> : Fragment() {

    @LayoutRes
    abstract fun attachLayout(): Int

    lateinit var presenter: P
    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(attachLayout(), container, false)
        presenter = attachPresenter()
        onInitializeViews()
        return rootView
    }

    abstract fun onInitializeViews()
    abstract fun attachPresenter(): P
}