package com.example.caira2.model

import retrofit2.http.Url
import java.net.URL

data class Course( val category: String,
                   val center_id: Double,
                   val duration: Double,
                   val id: Double,
                   val language: String,
                   val modality: String,
                   val name: String,
                   val price: Double,
                   val price_enrollment: Double,
                   val scholarship: String,
                   val vacancies: Double,
                   val vacancies_available: Double,
                  val centerName:String,
                  val centerImg:Int,
                         )
