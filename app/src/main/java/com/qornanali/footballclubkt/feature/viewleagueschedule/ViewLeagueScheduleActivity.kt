package com.qornanali.footballclubkt.feature.viewleagueschedule

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.qornanali.footballclub_kotlin.R
import com.qornanali.footballclubkt.adapter.ViewPagerAdapter


class ViewLeagueScheduleActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewleagueschedule)

        toolbar = findViewById(R.id.toolbar)
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)
        pagerAdapter = ViewPagerAdapter(supportFragmentManager)

        setSupportActionBar(toolbar)

        pagerAdapter.addFragment(DemoObjectFragment(), getString(R.string.next_matches))
        pagerAdapter.addFragment(DemoObjectFragment(), getString(R.string.last_matches))

        viewPager.adapter = pagerAdapter

        tabLayout.setupWithViewPager(viewPager)
    }
}

class DemoObjectFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.test_layout, container, false)
        val testText: TextView = rootView.findViewById(R.id.test_text)
        return rootView
    }
}
