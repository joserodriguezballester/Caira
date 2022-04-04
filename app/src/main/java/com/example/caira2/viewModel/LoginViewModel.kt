package com.example.caira2.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var rememberMe = MutableLiveData<Boolean>()
    var gotoRegister = MutableLiveData<Boolean>()

    init {
        gotoRegister.value=false
    }
    fun login() {
        //todo logica para guardar BD
        Log.i("msg*****//////////////////", "${email.value} ///// ${password.value}")
    }

    fun register() {
        //todo pasar a register
        Log.d("msg*****", "pasar a register")
        gotoRegister.value=true

    }

    fun forgot() {
        //todo pasar a forgot
        Log.d("msg*****", "pasar a forgot")
    }
}