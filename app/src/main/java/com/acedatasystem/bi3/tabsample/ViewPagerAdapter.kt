package com.acedatasystem.bi3.tabsample

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

class ViewPagerAdapter(
    frgamentManager: FragmentManager
) : FragmentStatePagerAdapter(frgamentManager) {

    private var fragmentList = emptyList<Fragment>()
    private var titleList = emptyList<String>()
    private var baseId = 0
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.count()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titleList[position]
    }

    fun addFragment(fLists: List<Fragment>, tLists: List<String>) {
        this.fragmentList = fLists
        this.titleList = tLists
        notifyChangeInPosition(1)
        notifyDataSetChanged()
    }

    fun notifyChangeInPosition(n: Int) {
        // shift the ID returned by getItemId outside the range of all previous fragments
        baseId += count + n
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE

    }
}