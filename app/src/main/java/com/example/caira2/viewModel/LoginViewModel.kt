package com.example.caira2.viewModel

import android.util.Log
import android.util.Patterns
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
import java.util.regex.Pattern


/**
 * Codigo del login un usuario (Login_fragment)
 */
class LoginViewModel : ViewModel() {
    var _email = MutableLiveData<String>()
    var email: LiveData<String> = _email

   var _password = MutableLiveData<String>()
    var password: LiveData<String> = _password

    var remembertoMe: Boolean = false

    var _loginResponse = MutableLiveData<Boolean>()
    var loginResponse: LiveData<Boolean> = _loginResponse

    var gotoRegister = MutableLiveData<Boolean>()

    private val _codigoError = MutableLiveData<Int?>()
    val codigoError: LiveData<Int?> = _codigoError

    private val _msgLiveData = MutableLiveData<String?>()
    val msgLiveData: LiveData<String?> = _msgLiveData

    private var _mostrarProgressBar = MutableLiveData<Boolean>()
    var mostrarProgressBar: LiveData<Boolean> = _mostrarProgressBar

    private lateinit var user: UserLogin

    init {
//        gotoRegister.value = false
//        _loginResponse.value = false
    }

    /**
     * Bindeado desde el XML Boton LOGIN
     * Recoje los datos [email] y [password],valida, hace peticion asincrona y obtiene [UserLogin]
     *
     */
    fun login() {
        //inicializa errores y mensajes
        _msgLiveData.postValue(null)
        _codigoError.postValue(null)

        // RECOJER DATOS FORMULARIO y Validar datos

        if (validar()){
            user = UserLogin(
                email = email.value.toString(),
                password = _password.value.toString()
            )
            Log.i("msg***** ", "fun login() user:$user")
            peticionServer(user)
        }
    }



    /**
     * Peticion Asincrona a la API
     *
     */
    private fun peticionServer(user: UserLogin) {
        _mostrarProgressBar.value=true
        viewModelScope.launch(Dispatchers.IO) {
            val response = LoginRepository.login_user(user)
            if (response is ApiResponse.Success) {
                Log.i("msg*****", "result${response.data.result}")

                if (response.data.result != null) {
                    //guardar en sharePreferents
                    if (remembertoMe) saveSharePreferents(response)
                    // logeado
                    _mostrarProgressBar.postValue(false)
                    _loginResponse.postValue(true)
                } else {
                    // usuario no logeado
                    _msgLiveData.postValue(response.data.message)
                    _codigoError.postValue(response.data.code.toInt())
                    _mostrarProgressBar.postValue(false)
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
     * Funcion ir a Registarse llamada desde la vista
     * Cambia un parametro visible desde la vista para cambiar de vista (activity)
     */
    fun register() {
        Log.d("msg*****", "pasar a register")
        gotoRegister.value = true
    }

    /**
     * Funcion He olvidado mi contrase??a
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

    /**
     * Valida los campos del formulario, muestra el error desde el livedata
     */
    private fun validar(): Boolean {
        if (email.value.isNullOrEmpty()) {
            _msgLiveData.postValue("No puede estar vacio")
            _codigoError.postValue(10)
            return false
        }
        if (password.value.isNullOrEmpty()) {
            _msgLiveData.postValue("No puede estar vacio")
            _codigoError.postValue(20)
            return false
        }



        val pattern: Pattern = Patterns.EMAIL_ADDRESS

        if (!pattern.matcher(email.value).matches()) {
            _msgLiveData.postValue("Tiene que ser un correo valido")
            _codigoError.postValue(11)
            return false
        }
        return true
    }
}