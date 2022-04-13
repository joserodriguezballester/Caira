package com.example.caira2.network.modelResponse

data class UserLoginResponse(
    val code: String,
    val message: String,
    val result: Result,
    val status: String
)

data class Result(
    val acces_token: String,
    val token_type: String
)