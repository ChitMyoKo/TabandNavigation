package com.acedatasystem.bi3.tabsample

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(
    frgamentManager : FragmentManager
): FragmentPagerAdapter(frgamentManager) {

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
    fun addFragment(fLists: List<Fragment>,tLists: List<String>)
    {
        this.fragmentList = fLists
        this.titleList = tLists
        notifyChangeInPosition(1)
        notifyDataSetChanged()
    }
    fun notifyChangeInPosition(n: Int) {
        // shift the ID returned by getItemId outside the range of all previous fragments
        baseId += count + n
    }

}