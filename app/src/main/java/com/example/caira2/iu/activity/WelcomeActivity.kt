package com.example.caira2.iu.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.caira2.R
import com.example.caira2.iu.adapter.WelcomeAdapter
import com.example.caira2.repository.WelcomeList
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.coroutines.MainScope


class WelcomeActivity : AppCompatActivity() {
    private lateinit var welcomeAdapter: WelcomeAdapter
    private val scope = MainScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_welcome)

        welcomeAdapter = WelcomeAdapter(this)
        view_pager_id.adapter = welcomeAdapter

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

// Indicar determinado
     //   val indicator:ProgressBar=findViewById(R.id.indeterminate_circular_indicator)

        //    indicator.visibility=View.VISIBLE


    }


    /**
     * Agrega un marcador de puntos al ViewPager
     */
    private fun addBottomDots(currentPage: Int) {
        linearPuntos.removeAllViews()
        val dotsSlide: Array<TextView?> =
            arrayOfNulls(WelcomeList.welcomeFrames.size) // numero de fragments
        for (i in dotsSlide.indices) {
            dotsSlide[i] = TextView(this)
            dotsSlide[i]!!.text = "\u2022"
            dotsSlide[i]!!.textSize = 52f
            dotsSlide[i]!!.setTextColor(resources.getColor(R.color.BlancoTransparente, null))
            linearPuntos.addView(dotsSlide[i])
        }
        // Marca la vista
        if (dotsSlide.size > 0) {
            dotsSlide[currentPage]!!.setTextColor(resources.getColor(R.color.secondary, null))
        }
    }
}