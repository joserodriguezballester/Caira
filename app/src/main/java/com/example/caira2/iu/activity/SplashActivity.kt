package com.example.caira2.iu.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.caira2.R
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Caira2)
        super.onCreate(savedInstanceState)
        Log.i("msg*****: ", "Splasscreen")
        // HACER AQUI EL LOGIN

//        Thread.sleep(1*1000);
//        Log.i("*****: ", "Splasscreen +1 ")
       goTo()
    }

    private fun goTo() {
        val intent = Intent(this, WelcomeActivity::class.java)
        //    val intent = Intent(this, RegisterActivity::class.java)
        //   val intent = Intent(this, BodyappActivity::class.java)
        startActivity(intent)
    }
}