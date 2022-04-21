package com.example.caira2.repository

import android.util.Log
import com.example.caira2.model.Course
import com.example.caira2.network.RetrofitInstance
import com.example.caira2.network.mappers.MapperImpl
import com.example.caira2.network.modelResponse.CourseResponse

object DashboardRepository {
    private var response=mutableListOf<CourseResponse>()
    private lateinit var responseMapeada: MutableList<Course>
   suspend fun all_courses():  MutableList<Course> {
        Log.i("msg*****", "suspend fun all_courses ")

        return try {
           response = RetrofitInstance.getRestApiServices().all_courses()
            ////////MAPEAR
            Log.i("msg*****", "Response service ${response.toString()}")
            val responseMapeada = response.map { com.example.caira2.network.mappers.MapperImpl.toCourseModel(it) }
            Log.i("msg*****", "ResponseMapeada ${responseMapeada.toString()}")

            return responseMapeada as MutableList<Course>
         //   return responseMapeada
        } catch (e: Exception) {
//            //handles no internet exception
            Log.i("msg*****", "suspend fun all_courses Response error ${e.toString()}")

            return emptyList<Course>().toMutableList<Course>()
        }
    }
}