package com.example.caira2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caira2.model.User
import com.example.caira2.network.modelResponse.ApiResponse
import com.example.caira2.repository.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    var name = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var repPassword = MutableLiveData<String>()
    var preferred = MutableLiveData<String>()
    private var _registerResponse = MutableLiveData<Boolean>()
    var registerResponse: LiveData<Boolean> = _registerResponse
    private val _codigoError= MutableLiveData<Int>()
    val codigoError:LiveData<Int> =_codigoError
    //    var _codigoError = MutableLiveData<Int>()
//    var codigoError :LiveData<Int> =_codigoError
    private val _msgLiveData = MutableLiveData<String?>()
    val msgLiveData: LiveData<String?> = _msgLiveData

    var msg = MutableLiveData<String>()
   // var codigoError: Int = 0
    private lateinit var user: User

    init {
        Log.i("msg*****", "Register:-- ")
        //       _registerResponse.value = false
    }

    fun register() {
        //todo controlar valores
        user = User(
            name = name.value.toString(),
            email = email.value.toString(),
            password = password.value.toString(),
            id = 1,
            language1 = null,
            lvl_language1 = null,
            preferred_course1 = preferred.value.toString(),
            preferred_course2 = null,
            url_instagram = null,
            url_linkedin = null,
            url_twitter = null,
            url_web = null,
            user_type = "Student"
        )

        //todo logica para guardar BD
        Log.i(
            "msg*****",
            "Register: ${name?.value}/// ${email.value} ///// ${password.value}///${preferred.value}"
        )
        var muser = User(
            name = "Jar",
            email = "jar2@gmail.com",
            password = "123",
            id = 1,
            language1 = null,
            lvl_language1 = null,
            preferred_course1 = "",
            preferred_course2 = null,
            url_instagram = null,
            url_linkedin = null,
            url_twitter = null,
            url_web = null,
            user_type = "Student"
        )

        llamarServidor(muser)
    }

    fun llamarServidor(user: User) {
        _msgLiveData.postValue(null)
        _codigoError.postValue(0)
        viewModelScope.launch(Dispatchers.IO) {
            val response = RegisterRepository.add_user(user)
            if (response is ApiResponse.Success) {
                Log.i(
                    "msg*****",
                    "response is ApiResponse.Success: ${name?.value}/// ${email.value} ///// ${password.value}///${preferred.value}"
                )
                _registerResponse.postValue(true)
            }
            if (response is ApiResponse.Error) {
                Log.i(
                    "msg*****",
                    "response is ApiResponse.Error: ${name?.value}/// ${email.value} ///// ${password.value}///${preferred.value}"
                )
                Log.i("msg*****", " Error: ${RegisterRepository.errorCode}")
                _codigoError.postValue(RegisterRepository.errorCode)
               // mostrarErrores()
                _registerResponse.postValue(false)
            }
        }
    }

     fun mostrarErrores() {


        when (_codigoError.value) {
            0 ->{
                Log.i("msg*****", "Reinicio de error")
                _msgLiveData.setValue(null)
            }
            400 -> {
                Log.i("msg*****", "entra en 400")
                _msgLiveData.setValue(RegisterRepository.errorMsg.detail)
            }
            500 ->{
                _msgLiveData.setValue(RegisterRepository.msgError)
            }
            else -> {
                _msgLiveData.setValue(RegisterRepository.errorLista.detail[0].msg)
            }

        }
    }
}
