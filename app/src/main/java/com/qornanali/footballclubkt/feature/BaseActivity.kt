package com.qornanali.footballclubkt.feature

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<P : BasePresenter<V>, V : BaseView> : AppCompatActivity()  {

    @LayoutRes
    abstract fun attachLayout(): Int

    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(attachLayout())
        presenter = attachPresenter()
        onInitializeViews()
    }

    abstract fun onInitializeViews()
    abstract fun attachPresenter(): P
}