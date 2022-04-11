package com.example.caira2.network.modelResponse

sealed class ApiResponse<T>(data: T?= null, exception: Exception? = null) {

    data class Success<T>(val data: T) : ApiResponse<T>(data, null)

    data class Error<T>( val exception: Exception) : ApiResponse<T>(null, exception)

}
//sealed class ApiResponse<Any>(data: Any?= null, exception: Exception? = null) {
//
//    data class Success<Any>(val data: Any?) : ApiResponse<Any?>(data, null)
//
//    data class Error<Any>(val data: Any?, val exception: Exception) : ApiResponse<Any?>(data, exception)
//
//}