package com.example.caira2.iu.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.caira2.iu.activity.ARG_OBJECT
import com.example.caira2.iu.activity.Welcome_item_Fragment

class WelcomeAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = Welcome_item_Fragment()
        val lista=listOf<String>("hola","adios","casi","haja")
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putString(ARG_OBJECT,lista[position])
        }
        return fragment
    }
}