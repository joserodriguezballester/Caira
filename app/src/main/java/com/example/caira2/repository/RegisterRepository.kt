package com.example.caira2.repository

import android.util.Log
import com.example.caira2.model.User
import com.example.caira2.network.ApiService
import com.example.caira2.network.mappers.MapperImpl
import com.example.caira2.network.modelResponse.ApiResponse
import com.example.caira2.network.modelResponse.Detail400
import com.example.caira2.network.modelResponse.Details
import com.example.caira2.network.modelResponse.UserResponse
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

object RegisterRepository {
    private lateinit var response: UserResponse
    lateinit var errorMsg: Detail400
    lateinit var errorLista: Details
    var msgError = ""
    var errorCode: Int = 0

    suspend fun add_user(user: User): ApiResponse<User> {
        // val response:UserResponse
        Log.i("msg*****", "suspend fun add_user ")
        return try {
            response = ApiService.getRestApiServices().add_user(user)
            Log.i("msg*****", "Response service ${response.toString()}")
            //Mapear resultado
            try {
                Log.i("msg*****", "Mapear")
                val userlist = mutableListOf<UserResponse>()
                userlist.add(0, response)
                val responseMapeada = userlist.map { MapperImpl.toUserModel(it) }
                Log.i("msg*****", "Response service USER: ${responseMapeada[0]}")
                ////Mapeado
                return ApiResponse.Success(data = responseMapeada[0])
            } catch (e: Exception) {
                Log.i("msg*****", "Error en mapeo:: ${e.message}")
                return  ApiResponse.Error(exception = e)
            }
        } catch (e: HttpException) {
            //handles exception with the request
            Log.i("msg*****", "HttpException ${e.toString()}")
            val responseError = ApiService.getRestApiServices().add_user_error(user)
            Log.i("msg*****", "Response valor error ${responseError.toString()}")
            errorCode = responseError.code()
            // Obtener el string de error devuelto por la API (ojo la API devuelve distintos JSON)
            when (errorCode) {
                400 -> {
                    var jsonObject = JSONObject(responseError.errorBody()?.string())
                    errorMsg = Gson().fromJson(jsonObject.toString(), Detail400::class.java)
                    Log.i("msg*****", "response.json=====--${errorMsg.detail}")
                    msgError = errorMsg.detail
                }
                422 -> {
                    var jsonObject = JSONObject(responseError.errorBody()?.string())
                    errorLista = Gson().fromJson(jsonObject.toString(), Details::class.java)
                    Log.i("msg*****", "response.json=====--${errorLista.detail[0].msg}")
                    msgError = errorLista.detail[0].msg
                }
                500 -> { // ¿de donde lo saco que lo pongo a pelo; xq no pongo los demas a pelo?
                    msgError = "Error de Servidor"
                }
                else -> {
                    var jsonObject = JSONObject(responseError.errorBody()?.string())
                    errorLista = Gson().fromJson(jsonObject.toString(), Details::class.java)
                    Log.i("msg*****", "response.json=====--${errorLista.detail[0].msg}")
                }
            }
            ApiResponse.Error(exception = e)
        } catch (e: IOException) {
            //handles no internet exception
            Log.i("msg*****", " IOException ${e.toString()}")
            ApiResponse.Error(exception = e)
        }
    }
}


