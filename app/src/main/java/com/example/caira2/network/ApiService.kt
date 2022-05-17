package com.example.caira2.network


import com.example.caira2.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit


const val BASE_URL = "https://apicaira.lunarxy.com/api/"
const val BASE_IMG_URL = "https://apicaira.lunarxy.com"


object ApiService {
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

object ApiWorker {
    private var mClient: OkHttpClient? = null
    private var mGsonConverter: GsonConverterFactory? = null

    /**
     * Don't forget to remove Interceptors (or change Logging Level to NONE)
     * in production! Otherwise people will be able to see your request and response on Log Cat.
     */
    val client: OkHttpClient
        @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
        get() {
            if (mClient == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    //       .addInterceptor(interceptor)  /// show all JSON in logCat
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level =
                            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                    })
                mClient = httpBuilder.build()

            }
            return mClient!!
        }
//    .addInterceptor(HttpLoggingInterceptor().apply {
//        level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
//    })

    val gsonConverter: GsonConverterFactory
        get() {
            if (mGsonConverter == null) {
                mGsonConverter = GsonConverterFactory
                    .create(
                        GsonBuilder()
                            .setLenient()
                            .disableHtmlEscaping()
                            .create()
                    )
            }
            return mGsonConverter!!
        }
}
