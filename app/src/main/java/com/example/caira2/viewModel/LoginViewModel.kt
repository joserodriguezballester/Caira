package com.example.caira2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caira2.CairaAplication.Companion.prefs
import com.example.caira2.model.UserLogin
import com.example.caira2.network.modelResponse.ApiResponse
import com.example.caira2.network.modelResponse.UserLoginResponse
import com.example.caira2.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Codigo del login un usuario (Login_fragment)
 */
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

    /**
     * Peticion a la API para logearse
     *
     * Recoje los datos [email] y [password], hace peticion asincrona y obtiene [UserLogin]
     */
    fun login() {
        //inicializa errores y mensajes
        _msgLiveData.postValue(null)
        _codigoError.postValue(null)

        // recoger datos
        user = UserLogin(
            email = email.value.toString(),
            password = _password.value.toString()
        )
        Log.i("msg*****///// fun login()/////////////", "${email.value} ///// ${password.value}")

        peticionServer(user)
        Log.i("msg*****//////////////////", "${email.value} ///// ${password.value}")
    }

    /**
     * Peticion Asincrona a la API
     *
     */
    private fun peticionServer(user: UserLogin) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = LoginRepository.login_user(user)
            if (response is ApiResponse.Success) {
                Log.i("msg*****", "result${response.data.result}")

                if (response.data.result != null) {
                    //guardar en sharePreferents
                    if (remembertoMe) saveSharePreferents(response)
                    // logeado
                    _loginResponse.postValue(true)
                } else {
                    // usuario no logeado
                    _msgLiveData.postValue(response.data.message)
                    _codigoError.postValue(response.data.code.toInt())
                    _loginResponse.postValue(false)
                }
            }
            // Error del Server
            if (response is ApiResponse.Error) {
                Log.i("msg*****", "ApiResponse.Error")
                _loginResponse.postValue(false)
            }
        }
    }

    /**
     * Funcion ir a Registarse llamada desde la vista <br/>
     * Cambia un parametro visible desde la vista para cambiar de vista (activity)
     */
    fun register() {
        Log.d("msg*****", "pasar a register")
        gotoRegister.value = true
    }

    /**
     * Funcion He olvidado mi contraseña
     * sin implementar
     */
    fun forgot() {
        //todo pasar a forgot
        Log.d("msg*****", "pasar a forgot")
    }

    /**
     * Guarda en Share Preferents
     *
     */
    fun saveSharePreferents(response: ApiResponse.Success<UserLoginResponse>) {
        Log.d("msg*****", "guardar preferents")
        prefs.saveResult(
            user.email,
            user.password,
            response.data.result.acces_token,
            response.data.result.token_type
        )
    }
}