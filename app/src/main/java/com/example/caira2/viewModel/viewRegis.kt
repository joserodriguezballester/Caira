package com.example.caira2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class viewRegis: ViewModel() {
    var name = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var repPassword = MutableLiveData<String>()
    var registerResponse= MutableLiveData<Boolean>()
init{
    Log.i("msg*****", "Register:-- ")
    registerResponse.value=false
}
    fun onButtonClicks(){
        Log.i("msg*****", "click ")
    }

    fun register(){
        //todo logica para guardar BD
        Log.i("msg*****", "Register: ${name.value} ${email.value} ///// ${password.value}")
        // todo
        var registrado:Boolean=true
        if (registrado)   {
            registerResponse.value=true
        }else{
            registerResponse.value=false
        }
    }



}