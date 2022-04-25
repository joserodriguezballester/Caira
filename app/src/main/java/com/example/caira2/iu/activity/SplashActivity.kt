package com.example.caira2.iu.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.caira2.CairaAplication
import com.example.caira2.viewModel.SplashViewModel

class SplashActivity : AppCompatActivity() {
    private lateinit var viewModel: SplashViewModel
    private lateinit var mintent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
         val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        Log.i("msg*****: ", "Splasscreen")
        // HACER AQUI EL LOGIN
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        viewModel.initialLogin()
        viewModel.loginResponse.observe(this) { value ->
            if (value) {
                Log.i("msg*****", "gotoDashboard: ${viewModel.loginResponse} ")
                mintent = Intent(this, BodyappActivity::class.java)

            } else {
                Log.i("msg*****", "gotoWelcome: ${viewModel.loginResponse} ")
                mintent = Intent(this, WelcomeActivity::class.java)
            }
            startActivity(mintent)
        }

      //  finish()
    }

    private fun goTo() {

        val intent = Intent(this, WelcomeActivity::class.java)
        //    val intent = Intent(this, RegisterActivity::class.java)
        //   val intent = Intent(this, BodyappActivity::class.java)
        startActivity(intent)
    }
}