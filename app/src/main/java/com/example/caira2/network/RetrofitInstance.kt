package com.example.caira2.network


import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://apicaira.lunarxy.com/api/"
const val BASE_IMG_URL = "https://apicaira.lunarxy.com"


object RetrofitInstance {
 //   val BASE_URL = "https://apicaira.lunarxy.com/api/"

    /**
     * Inserta token de autorización
     */
    private fun client(token: String): OkHttpClient? {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        }.build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun retrofit2(token: String) = Retrofit.Builder()
        .client(client(token))
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRestApiServices(): RestApiService {
        return retrofit.create(RestApiService::class.java)
    }

    fun getApiServicesToken(token: String): RestApiService {
        return retrofit2(token).create(RestApiService::class.java)
    }
}


//const val AUTH_HEADER = "Authorization"
//const val API_KEY = "Jw0oIMgpId1" //Add Valid API Key from yelp
//object RetrofitInstance {
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .client(
//            OkHttpClient()
//                .newBuilder()
//                .addInterceptor { chain ->
//                    chain.proceed(chain
//                        .request()
//                        .newBuilder()
//                        .addHeader(AUTH_HEADER, "BEARER $API_KEY")
//                        .build()
//                    )
//                }
//                .build()
//        )
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    fun getYelpServices(): YelpServices {
//        return retrofit.create(YelpServices::class.java)
//    }
//}