package com.example.caira2

import android.app.Application
import com.example.caira2.model.Prefs

class CairaAplication : Application() {
    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}