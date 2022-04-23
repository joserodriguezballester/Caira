package com.example.caira2.iu.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.caira2.R
import com.example.caira2.iu.adapter.WelcomeAdapter
import com.example.caira2.repository.WelcomeList
import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeActivity : AppCompatActivity() {
    private lateinit var welcomeAdapter: WelcomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_welcome)

        welcomeAdapter = WelcomeAdapter(this)
        view_pager_id.adapter = welcomeAdapter
        // addBottomDots(0)
        view_pager_id.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                addBottomDots(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }

    //
    //    * Agrega un marcador de punto en la parte inferior
    //
    private fun addBottomDots(currentPage: Int) {
        Log.i("*****Puntos: ", "pos: ${currentPage}")
        linearPuntos.removeAllViews()
        val dotsSlide: Array<TextView?> =
            arrayOfNulls(WelcomeList.welcomeFrames.size) // numero de fragments
        Log.i("*****Puntos: ", "size: ${WelcomeList.welcomeFrames.size}")
        for (i in dotsSlide.indices) {
            Log.i("*****Puntos: ", "i: ${i}")
            dotsSlide[i] = TextView(this)
            dotsSlide[i]!!.text = "\u2022"
            dotsSlide[i]!!.textSize = 52f
            dotsSlide[i]!!.setTextColor(resources.getColor(R.color.BlancoTransparente, null))
            linearPuntos.addView(dotsSlide[i])
        }

        // Marca la vista
        if (dotsSlide.size > 0) {
            dotsSlide[currentPage]!!.setTextColor(resources.getColor(R.color.secondary, null))
            Log.i("*****Puntos: ", "pos(if): ${currentPage}")
        }
    }


}