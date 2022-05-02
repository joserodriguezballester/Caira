package com.example.caira2.iu.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.caira2.viewModel.SplashViewModel


class SplashActivity : AppCompatActivity() {
    private lateinit var viewModel: SplashViewModel
    private lateinit var mintent: Intent
    var isReady = false
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        Log.i("msg*****: ", "Splashcreen")
        // HACER AQUI EL LOGIN
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        viewModel.initialLogin()

        if(isInternetAvailable(this)) {
            Log.i("msg*****", "con internet ")
        }else{
            Log.i("msg*****", "sin internet ")
            Toast.makeText(this,"Conectate a Internet",Toast.LENGTH_LONG).show()
        }

  //      goTo()

//        val content: View = findViewById(android.R.id.content)
//        content.viewTreeObserver.addOnPreDrawListener(
//            object : ViewTreeObserver.OnPreDrawListener {
//                override fun onPreDraw(): Boolean {
//                    // Check if the initial data is ready.
//                    return if (isReady) {
//                        // The content is ready; start drawing.
//                        content.viewTreeObserver.removeOnPreDrawListener(this)
//                        true
//                    } else {
//                        // The content is not ready; suspend.
//                        false
//                    }
//                }
//            }
//        )

        viewModel.loginResponse.observe(this) { value ->
            if (value) {
                Log.i("msg*****", "iendo a gotoDashboard: ${viewModel.loginResponse} ")
                mintent = Intent(this, BodyappActivity::class.java)

            } else {
                Log.i("msg*****", "gotoWelcome: ${viewModel.loginResponse} ")
                mintent = Intent(this, WelcomeActivity::class.java)

            }
            // splashScreen.setKeepOnScreenCondition { true }
            startActivity(mintent)
            isReady = true
        }


        //  finish()

    }
    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        Log.i("msg*****", " internet::$result ")
       
        return result

    }

    private fun goTo() {

        val intent = Intent(this, WelcomeActivity::class.java)
      //     val intent = Intent(this, RegisterActivity::class.java)
        // val intent = Intent(this, BodyappActivity::class.java)
        startActivity(intent)
    }
}