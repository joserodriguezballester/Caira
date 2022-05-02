package com.example.caira2.network.modelResponse

data class CenterResponse(
    val id: Int,
    val acronym: String,
    val type_center: String,
    val name: String,
    val email: String,
    val location: String,
    val logo: String?,
    val banner: String?,
    val description: String,
    val website: String?
)