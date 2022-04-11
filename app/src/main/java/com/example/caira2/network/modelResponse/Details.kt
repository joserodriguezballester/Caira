package com.example.caira2.network.modelResponse

data class Details(val detail: List<Detail>)

data class Detail(
    val loc: List<String>,
    val msg: String,
    val type: String
)
data class Detail400(val detail:String)

