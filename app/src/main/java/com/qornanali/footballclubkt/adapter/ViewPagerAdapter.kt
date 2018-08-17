package com.qornanali.footballclubkt.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.qornanali.footballclubkt.feature.viewleagueschedule.DemoObjectFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    val fragments = ArrayList<Fragment>()
    var fragmentsTitles = ArrayList<String>()

    override fun getCount(): Int{
        return fragmentsTitles.size
    }

    override fun getItem(i: Int): Fragment {
        return fragments.get(i)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentsTitles.get(position)
    }

    fun addFragment(fragment: Fragment, title: String){
        fragments.add(fragment)
        fragmentsTitles.add(title)
    }
}
