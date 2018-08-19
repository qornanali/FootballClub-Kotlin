package com.qornanali.footballclubkt.util.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class SchedulePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    val fragments = ArrayList<Fragment>()
    var fragmentsTitles = ArrayList<String>()

    override fun getCount(): Int {
        return fragmentsTitles.size
    }

    override fun getItem(i: Int): Fragment {
        val fragment = fragments.get(i)
        fragment.arguments = Bundle().apply {
            putString("title", fragmentsTitles.get(i))
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentsTitles.get(position)
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        fragmentsTitles.add(title)
    }
}
