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
   // val name: LiveData<String> = _name
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var repPassword = MutableLiveData<String>()
     var preferred = MutableLiveData<String>()
   // var preferred: LiveData<String> = _preferred
    private var _registerResponse = MutableLiveData<Boolean>()
    var registerResponse: LiveData<Boolean> = _registerResponse
    var _codigo = MutableLiveData<Int>()
    var codigo: LiveData<Int> = _codigo

//
//    private val _nameLiveData = MutableLiveData<String>()
//    val nameLiveData: LiveData<String>
//        get() = _nameLiveData
//    fun setFirstName() {
//        _nameLiveData.postValue( name)
//    }
//

    private lateinit var user: User

    init {
        Log.i("msg*****", "Register:-- ")
        _registerResponse.value = false
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
        //      var codigo:Int=llamarServidor(user)

        Log.i(
            "msg*****",
            "Register: ${name?.value}/// ${email.value} ///// ${password.value}///${preferred.value}"
        )
        // todo


//        var myUser: User = User(
//            name = "Jar",
//            email = "jar3@gmail.com",
//            password = "string",
//            user_type = "string",
//            language1 = "string",
//            lvl_language1 = "string",
//            preferred_course1 = "string",
//            preferred_course2 = "string",
//            url_linkedin = "string",
//            url_instagram = "string",
//            url_twitter = "string",
//            url_web = "string",
//            id = 1
//        )
        llamarServidor(user)
    }

    fun llamarServidor(user: User) {
        Log.i("msg*****", "Llamando servidor")
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
                Log.i("msg*****", " Error: ${response.exception}")
                _registerResponse.postValue(false)
            }
        }
    }
}
//        val restApiService = RetrofitHelper.getInstance().create(RestApiService::class.java)
//        // launching a new coroutine
//        GlobalScope.launch {
//            val result = restApiService.add_user(user)
//            if (result != null)
//                if (result.body()!=null)
//            // Checking the results
//                Log.d("*****: ", result.body().toString())
//            var jsonObject: JSONObject = JSONObject(result?.errorBody()?.string())
//            val data: Details = Gson().fromJson(jsonObject.toString(), Details::class.java)
//            Log.d("*****: ", data.detail.toString())
//        }
//       return codig
//   }

//}