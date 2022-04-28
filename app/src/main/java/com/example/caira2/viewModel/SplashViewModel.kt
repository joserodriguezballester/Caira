package com.example.caira2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caira2.CairaAplication
import com.example.caira2.model.UserLogin
import com.example.caira2.network.modelResponse.ApiResponse
import com.example.caira2.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private var _loginResponse = MutableLiveData<Boolean>()
    var loginResponse: LiveData<Boolean> = _loginResponse

    /**
     * Peticion Asincrona a la API
     *
     */
    private fun loginUser(user: UserLogin) {
        Log.i("msg*****", "loginUser(user: UserLogin)")
        viewModelScope.launch(Dispatchers.IO) {
            val response = LoginRepository.login_user(user)
            Log.i("msg*****", "loginUser(user: UserLogin)::${user}")
            if (response is ApiResponse.Success) {
                Log.i("msg*****", "result${response.data.result}")
                if (response.data.result != null) {
                    // logeado
                    _loginResponse.postValue(true)
                } else {
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
     * Hace una peticion de login con los valores de SharePreferences
     */
    fun initialLogin() {
        Log.i("msg*****: ", "getDataPreferences() ")
        //   comprobar si existen prefs
        val valores = CairaAplication.prefs.getResult()
        if (valores!!.keys.isEmpty()) {
            Log.i("msg*****: ", "prefs empty ${valores.keys}${valores.values}}")
            _loginResponse.postValue(false)
        } else {
            Log.i("msg*****: ", "prefs not empty ${valores.keys}${valores.values}}")
            var user = UserLogin(
               valores.getValue("userMail").toString(),
                valores.getValue("userPass").toString()
            )
            Log.i("msg*****: ", "prefs not empty user $user")
            loginUser(user)
        }
    }
}