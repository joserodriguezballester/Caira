package com.example.caira2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caira2.model.UserLogin
import com.example.caira2.network.modelResponse.ApiResponse
import com.example.caira2.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var _email = MutableLiveData<String>()
    var email: LiveData<String> = _email

    var _password = MutableLiveData<String>()
    var password: LiveData<String> = _password

    var remembertoMe: Boolean = false

    private var _loginResponse = MutableLiveData<Boolean>()
    var loginResponse: LiveData<Boolean> = _loginResponse

    var gotoRegister = MutableLiveData<Boolean>()

    private val _codigoError = MutableLiveData<Int?>()
    val codigoError: LiveData<Int?> = _codigoError

    private val _msgLiveData = MutableLiveData<String?>()
    val msgLiveData: LiveData<String?> = _msgLiveData

    private lateinit var user: UserLogin

    init {
        gotoRegister.value = false
        _loginResponse.value = false
    }

    fun login() {

        // recoger datos
        _msgLiveData.postValue(null)
        _codigoError.postValue(null)

        user = UserLogin(
            email = email.value.toString(),
            password = _password.value.toString()
        )
        // Peticion al servidor
        Log.i("msg*****///// fun login()/////////////", "${email.value} ///// ${password.value}")
        peticionServer(user)
        Log.i("msg*****//////////////////", "${email.value} ///// ${password.value}")
    }

    private fun peticionServer(user: UserLogin) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = LoginRepository.login_user(user)
            if (response is ApiResponse.Success) {
                Log.i(
                    "msg*****",
                    "response is ApiResponse.Success: // ${response.data.result} "
                )
                if (response.data.result != null) {
                    // logeado
                    _loginResponse.postValue(true)
                } else {
                    _msgLiveData.postValue(response.data.message)
                    _codigoError.postValue(response.data.code.toInt())
                    _loginResponse.postValue(false)
                }
            }
            if (response is ApiResponse.Error) {
                Log.i(
                    "msg*****",
                    "response is ApiResponse.Error: /// ${email.value} ///// ${password.value}//"
                )
                _loginResponse.postValue(false)
            }
        }
    }

    fun register() {
        //todo pasar a register
        Log.d("msg*****", "pasar a register")
        gotoRegister.value = true
    }

    fun forgot() {
        //todo pasar a forgot
        Log.d("msg*****", "pasar a forgot")
    }

    fun guardarSharePreferents() {
        Log.d("msg*****", "guardar preferents")
    }
}