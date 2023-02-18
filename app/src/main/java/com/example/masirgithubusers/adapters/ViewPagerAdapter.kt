package com.example.masirgithubusers.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.masirgithubusers.view.FollowFrag


class ViewPagerAdapter(activity: FragmentActivity?) : FragmentStateAdapter(activity!!) {


    private val mFragmentList: MutableList<Fragment> = ArrayList()
    private val mFragmentTitleList: MutableList<String> = ArrayList()

    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    public fun getTabTitle(position : Int): String{
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }
}