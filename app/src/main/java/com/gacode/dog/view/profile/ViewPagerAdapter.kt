package com.gacode.dog.view.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gacode.dog.base.BaseMVPFragment


import com.gacode.dog.view.home.HomeActivity
import com.gacode.dog.view.message.MessageActivity
import com.gacode.dog.view.profile.dogs.DogsActivity
import com.gacode.dog.view.profile.historyBooking.HistoryBookingActivity
import com.gacode.dog.view.profile.services.ServicesActivity


public class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, num_tabs:Int, type:String) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val num_tab = num_tabs
    private val typeAccount = type


    override fun getItemCount(): Int {
        return num_tab
    }

    override fun createFragment(position: Int): Fragment {
       if(typeAccount == "1")when (position) {
            0 -> return DogsActivity()
            1 -> return HistoryBookingActivity()
        }

        if(typeAccount == "2")when (position) {
            0 -> return ServicesActivity()
            1 -> return HistoryBookingActivity()
        }
        return DogsActivity()
    }
}