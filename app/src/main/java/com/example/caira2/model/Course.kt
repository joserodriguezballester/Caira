package com.example.caira2.model

import com.example.caira2.network.modelResponse.CenterResponse
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Url
import java.io.Serializable
import java.net.URL

data class Course (  val category: String,
                     val center_id: Double,
                     val duration: Double,
                     val id: Double,
                     val language: String,
                     val modality: String,
                     val name: String,
                     val price: Double,
                     val price_enrollment: Double?,
                     val scholarship: String?,
                     val vacancies: Double,
                     val vacancies_available: Double?,
                     val description:String,
                     val location: String,
                     val image: String,
                     val centerAcronyum:String,
                     val centerName: String,
                     val centerlogo:String?,
                         ):Serializable
