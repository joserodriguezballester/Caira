package com.example.caira2.repository

import android.util.Log
import com.example.caira2.CairaAplication.Companion.prefs
import com.example.caira2.model.Center
import com.example.caira2.network.RetrofitInstance
import com.example.caira2.network.modelResponse.CenterResponse

object CentersRepository {
    //   private var response=mutableListOf<CenterResponse>()
    private lateinit var response: Any

    private lateinit var responseMapeada: MutableList<Center>

    ///  ////////// REVISAR ///////////////////////////////////////////////////
    suspend fun all_centers(): MutableList<Center> {
        Log.i("msg*****", "suspend fun all_centers ")
        return try {
            //       response = RetrofitInstance.getRestApiServices().all_centers2()
            val token =prefs.getToken()
            response = RetrofitInstance.getApiServicesToken(token).all_centers()

            ////////MAPEAR
            Log.i("msg*****", "Response service ${response.toString()}")
            val responseMapeada = (response as MutableList<CenterResponse>).map {
                com.example.caira2.network.mappers.MapperImpl.toCenterModel(it)
            }
            Log.i("msg*****", "ResponseMapeada ${responseMapeada.toString()}")

            return responseMapeada as MutableList<Center>

        } catch (e: Exception) {
//            //handles no internet exception
            Log.i("msg*****", "suspend fun all_centers Response error ${e.toString()}")

            return emptyList<Center>().toMutableList<Center>()
        }
    }
}