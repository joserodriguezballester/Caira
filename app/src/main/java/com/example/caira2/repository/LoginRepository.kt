package com.example.caira2.repository

import android.util.Log
import com.example.caira2.model.User
import com.example.caira2.model.UserLogin
import com.example.caira2.network.RetrofitInstance
import com.example.caira2.network.mappers.MapperImpl
import com.example.caira2.network.modelResponse.*
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

object LoginRepository {

    private lateinit var response:UserLoginResponse
    lateinit var errorMsg: Detail400
    lateinit var errorLista: Details
    var msgError=""
    var errorCode :Int=0

    suspend fun login_user(user: UserLogin): ApiResponse<UserLoginResponse> {
        Log.i("msg*****", "suspend fun login user ${user.email} ///${user.password} ")

        return try {
            response = RetrofitInstance.getRestApiServices().login_user(user)
            Log.i("msg*****", "Response service ${response.toString()}")
            return ApiResponse.Success(data = response)

        } catch (e: HttpException) {
            //handles exception with the request
            Log.i("msg*****", "Exception ${e.toString()}")
            Log.i("msg*****", "Response valor error ${response.toString()}")
            ApiResponse.Error(exception = e)
        } catch (e: IOException) {
            //handles no internet exception
            Log.i("msg*****", "Response error2 ${e.toString()}")
            ApiResponse.Error( exception = e)
        }
    }
}