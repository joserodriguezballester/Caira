package com.example.caira2.viewModel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caira2.CairaAplication
import com.example.caira2.CairaAplication.Companion.prefs
import com.example.caira2.model.User
import com.example.caira2.model.UserLogin
import com.example.caira2.network.modelResponse.ApiResponse
import com.example.caira2.repository.LoginRepository
import com.example.caira2.repository.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegisterViewModel : ViewModel() {
    var name = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var repPassword = MutableLiveData<String>()
    var preferred = MutableLiveData<String>()

    private var _registerResponse = MutableLiveData<Boolean>()
    var registerResponse: LiveData<Boolean> = _registerResponse

    private val _codigoError = MutableLiveData<Int>()
    val codigoError: LiveData<Int> = _codigoError

    private val _msgLiveData = MutableLiveData<String?>()
    val msgLiveData: LiveData<String?> = _msgLiveData

    private lateinit var user: User

    /**
     *
     * Bindeado desde el XML Boton REGISTER
     * Recoje los datos ,valida, hace peticion asincrona
     *
     */
    fun register() {
        Log.i("msg*****", "register")
        // inicializa errores y mensajes
        _msgLiveData.value = null
        _codigoError.value = 0

        // RECOJER DATOS FORMULARIO y Validar datos
        if (validar()) {
            Log.i("msg*****", "validar ${validar()}")
            // usuario a partir del formulario
            try {
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
                Log.i("msg*****", "Valores Usuario: ${user}")
            } catch (e: Exception) {
                Log.i("msg*****", "catch: ${e.message}")
            }
            // Llamada a la API
            registrarUsuario(user)
        }
    }

    /**
     * Llamada a la API para registrar usuario;
     * ademas logea al usuario para obtener token
     */
    private fun registrarUsuario(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RegisterRepository.add_user(user)

            if (response is ApiResponse.Success) {
                Log.i("msg*****", "response is ApiResponse.Success: $user")
                // logearse para obtener token
                loginToTakeToken(user)
                _registerResponse.postValue(true)
            }
            // usuario no registrado
            if (response is ApiResponse.Error) {
                Log.i("msg*****", "response is ApiResponse.Success: $user")
                Log.i("msg*****", " Error: ${RegisterRepository.errorCode}")
                _codigoError.postValue(RegisterRepository.errorCode)
                _registerResponse.postValue(false)
            }
        }
    }


    /**
     *  Asignar texto a los codigos de error devueltos por la API
     */
    fun mostrarErrores() {
        when (_codigoError.value) {
            //inicializa mensaje error
            0 -> _msgLiveData.setValue(null)

            // usuario ya existe
            400 -> _msgLiveData.setValue(RegisterRepository.msgError)
            // nombre por mayuscula
            422 -> _msgLiveData.setValue(RegisterRepository.msgError)
            // Error de Servidor
            500 -> _msgLiveData.setValue(RegisterRepository.msgError)

            else -> {
                Log.i("msg*****", "Error no implementado ${_codigoError.value}")
            }
        }
    }

    /**
     * Valida los campos del formulario, muestra el error desde el livedata
     */
    private fun validar(): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        if (email.value.isNullOrBlank()) {
            _msgLiveData.postValue("No puede estar vacio")
            _codigoError.postValue(10)
            return false
        }
        if (!pattern.matcher(email.value).matches()) {
            _msgLiveData.postValue("Tiene que ser un correo valido")
            _codigoError.postValue(11)
            return false
        }
        if (password.value.isNullOrBlank()) {
            _msgLiveData.postValue("No puede estar vacio")
            _codigoError.postValue(20)
            return false
        }
        if (repPassword.value.isNullOrBlank()) {
            _msgLiveData.postValue("No puede estar vacio")
            _codigoError.postValue(30)
            return false
        }
        if (password.value != repPassword.value) {
            _msgLiveData.postValue("las contraseñas no coinciden")
            _codigoError.postValue(31)
            return false
        }
        if (name.value.isNullOrBlank()) {
            _msgLiveData.postValue("No puede estar vacio")
            _codigoError.postValue(40)
            return false
        }
        if (preferred.value.isNullOrBlank()) {
            _msgLiveData.postValue("No puede estar vacio")
            _codigoError.postValue(50)
            return false
        }
        return true
    }


    /**
     * Loguea al usuario registrado para obtener el token
     *
     */
    private fun loginToTakeToken(user: User) {
        Log.i("msg*****", "loginToToken $user")
        var userLogin = UserLogin(user.email, user.password)
        viewModelScope.launch(Dispatchers.IO) {
            val response = LoginRepository.login_user(userLogin)
            if (response is ApiResponse.Success) {
                Log.i("msg*****", "result${response.data.result}")

                if (response.data.result != null) {
                    // usuario logeado
                    Log.i("msg*****", "usuario logeado")
                   prefs.saveResult(
                        user.email,
                        user.password,
                        response.data.result.acces_token,
                        response.data.result.token_type
                    )
                    Log.i("msg*****", "usuario logeado y con token")

                } else {
                    // usuario no logeado
                    Log.i("msg*****", "usuario no logeado")
                }
            }
            // Error del Server // usuario no logeado
            if (response is ApiResponse.Error) {
                Log.i("msg*****", "ApiResponse.Error")
            }
        }

    }
}
