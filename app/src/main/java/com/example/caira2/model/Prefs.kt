package com.example.caira2.model

import android.content.Context
import android.util.Log

class Prefs (val context: Context) {

    val SHARED_NAME ="Mydtb"
    val SHARED_UserMail ="userMail"
    val SHARED_UserPass ="userPass"
    val SHARED_ROL ="Rol"
    val SHARED_TOKEN ="Token"
    val SHARED_TOKEN_TYPE ="TokenType"

    val storage=context.getSharedPreferences(SHARED_NAME,0)

    /**
     * Guarda en SharePreferences
     */
    fun saveResult(userId:String,userPass:String,token:String,tokenType:String){
     //   storage.edit().putInt(SHARED_ROL,rol).apply()
        storage.edit().putString(SHARED_UserMail,userId).apply()
        storage.edit().putString(SHARED_UserPass,userPass).apply()
        storage.edit().putString(SHARED_TOKEN,token).apply()
        storage.edit().putString(SHARED_TOKEN_TYPE,tokenType).apply()
    }

    fun getResult(): MutableMap<String, *>? = storage.all

// Devolver un resultado
//    fun getUserpass():String = storage.getString(SHARED_UserPass,"")!!
fun getToken():String = storage.getString(SHARED_TOKEN,"")!!

    fun closeSesion(){
        storage.edit().clear().apply()
        Log.i("prefs(clear):", storage.toString())
    }
}