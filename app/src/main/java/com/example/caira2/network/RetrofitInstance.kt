package com.example.caira2.network


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://apicaira.lunarxy.com/"

object RetrofitHelper {
    val BASE_URL = "https://apicaira.lunarxy.com/"
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object RetrofitInstance {
    val BASE_URL = "https://apicaira.lunarxy.com/"
//    const val BASE_URL = "https://apicaira.lunarxy.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRestApiServices(): RestApiService {
        return retrofit.create( RestApiService::class.java)

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