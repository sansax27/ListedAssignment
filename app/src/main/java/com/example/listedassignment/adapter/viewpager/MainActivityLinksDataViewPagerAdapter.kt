package com.example.listedassignment.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.listedassignment.data.models.LinksDataModel
import com.example.listedassignment.ui.fragment.LinksTabsContentFragment

class MainActivityLinksDataViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle,
                                            private val topLinks: List<LinksDataModel>,
                                            private val recentLinks: List<LinksDataModel>)
    : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> LinksTabsContentFragment(topLinks)
            else -> LinksTabsContentFragment(recentLinks)
        }
    }
}