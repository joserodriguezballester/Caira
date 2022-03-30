package com.example.caira2.iu.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.caira2.R
import com.example.caira2.iu.adapter.WelcomeAdapter

import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeActivity : AppCompatActivity() {
    private lateinit var welcomeAdapter: WelcomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        welcomeAdapter = WelcomeAdapter(this)
        view_pager_id.adapter=welcomeAdapter
    }

}