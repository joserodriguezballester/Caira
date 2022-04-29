package com.example.caira2.network.modelResponse

/**
 * Clase devuelta por API para errores de add_user (menos error 400)
 */
data class Details(val detail: List<Detail>)

data class Detail(
    val loc: List<String>,
    val msg: String,
    val type: String
)

/**
 * Clase devuelta por API para error 400 de add_user
 */
data class Detail400(val detail:String)

