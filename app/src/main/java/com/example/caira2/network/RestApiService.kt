package com.example.caira2.network

import com.example.caira2.model.User
import com.example.caira2.model.UserLogin
import com.example.caira2.network.modelResponse.CenterResponse
import com.example.caira2.network.modelResponse.CourseResponse
import com.example.caira2.network.modelResponse.UserLoginResponse
import com.example.caira2.network.modelResponse.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RestApiService {


    @POST("add_user")
    suspend fun add_user(@Body user: User): UserResponse
    //(add_user devuelve distintos JSON por eso hay que hacer otra llamada para obtener el error)
    @POST("add_user")
    suspend fun add_user_error(@Body user: User): Response<User>

    @POST("user_login")
    suspend fun login_user(@Body user: UserLogin): UserLoginResponse

    @GET("all_courses")
    suspend fun all_courses(): MutableList<CourseResponse>

    @GET("all_centers")
    suspend fun all_centers(): MutableList<CenterResponse>

    @GET("get_courses/{id}")
    suspend fun all_courses_one_center(@Path("id") id:Int): MutableList<CourseResponse>

    /// DEBUG

    @GET("all_courses")
    suspend fun all_courses2(): Any

    @GET("all_centers")
    suspend fun all_centers2(): Any

    @POST("user-login")
    suspend fun login_user2(@Body user: UserLogin): Any


}