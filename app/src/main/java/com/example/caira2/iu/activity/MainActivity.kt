package com.example.caira2.iu.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.caira2.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goTo()
    }

    fun goTo() {
      //  val intent = Intent(this, WelcomeActivity::class.java)
         val intent = Intent(this, RegisterActivity::class.java)
       //  val intent = Intent(this, BodyappActivity::class.java)

        startActivity(intent)
    }
}