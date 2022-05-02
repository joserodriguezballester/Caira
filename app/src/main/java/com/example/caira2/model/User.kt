package com.example.caira2.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String,
    @SerializedName("password") val password: String,
    @SerializedName("id") val id: Int?,
    @SerializedName("language1") val language1: String?,
    @SerializedName("lvl_language1") val lvl_language1: String?,
    @SerializedName("preferred_course1") val preferred_course1: String?,
    @SerializedName("preferred_course2") val preferred_course2: String?,
    @SerializedName("url_instagram") val url_instagram: String?,
    @SerializedName("url_linkedin") val url_linkedin: String?,
    @SerializedName("url_twitter") val url_twitter: String?,
    @SerializedName("url_web") val url_web: String?,
    @SerializedName("user_type") val user_type: String?,
    @SerializedName("logo") val logo: String?,
    @SerializedName("banner") val banner: String?

)
