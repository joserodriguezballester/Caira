package com.example.caira2.network

import com.example.caira2.model.User
import com.example.caira2.model.UserLogin
import com.example.caira2.network.modelResponse.ApiResponse
import com.example.caira2.network.modelResponse.UserLoginResponse
import com.example.caira2.network.modelResponse.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RestApiService {

    @POST("add_user")
    suspend fun add_user(@Body user: User): UserResponse

    @POST("add_user")
    suspend fun add_user_error(@Body user: User): Response<User>

    @POST("user-login")
    suspend fun login_user(@Body user: UserLogin): UserLoginResponse
}