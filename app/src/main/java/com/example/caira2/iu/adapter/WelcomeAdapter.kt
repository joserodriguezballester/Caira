package com.example.caira2.iu.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.caira2.iu.activity.ARG_OBJECT
import com.example.caira2.iu.activity.Welcome_item_Fragment
import com.example.caira2.repository.WelcomeList

class WelcomeAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = WelcomeList.welcomeFrames.size

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = Welcome_item_Fragment()
        fragment.arguments = Bundle().apply {
            putSerializable(ARG_OBJECT, WelcomeList.welcomeFrames[position])
        }
        return fragment
    }
}