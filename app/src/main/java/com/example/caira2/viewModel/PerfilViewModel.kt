package com.example.caira2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.caira2.CairaAplication.Companion.prefs

class PerfilViewModel : ViewModel() {
    var _name = MutableLiveData<String>().apply {
        value = prefs.getMail()
    }
    var userName = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var language = MutableLiveData<String>()
    var preferred = MutableLiveData<String>()

    private val _text = MutableLiveData<String>().apply {
        pedirDatos()
        value = "This is slideshow Fragment"
    }

    private fun pedirDatos() {
        Log.i("msg*****", "pedir datos perfil")
    }

    val text: LiveData<String> = _text
    val name: LiveData<String> = _name

    fun update() {

    }

}