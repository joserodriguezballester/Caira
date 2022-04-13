package com.example.caira2.model

import com.google.gson.annotations.SerializedName

data class UserLogin(@SerializedName("email") var email: String,
                     @SerializedName("password") val password: String)
