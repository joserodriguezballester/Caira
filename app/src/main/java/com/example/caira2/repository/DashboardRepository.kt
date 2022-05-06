package com.example.caira2.repository

import android.util.Log
import com.example.caira2.model.Course
import com.example.caira2.network.RetrofitInstance
import com.example.caira2.network.modelResponse.CourseResponse

object DashboardRepository {
    private var response = mutableListOf<CourseResponse>()
    private lateinit var responseMapeada: MutableList<Course>

    suspend fun all_courses(): MutableList<Course> {
        Log.i("msg*****", "suspend fun all_courses ")

        return try {
            response = RetrofitInstance.getRestApiServices().all_courses()
            ////////MAPEAR
            Log.i("msg*****", "Response service ${response.toString()}")
            responseMapeada =
                response.map { com.example.caira2.network.mappers.MapperImpl.toCourseModel(it) }
                        as MutableList<Course>
            Log.i("msg*****", "ResponseMapeada ${responseMapeada.toString()}")
            return responseMapeada

        } catch (e: Exception) {
//            //handles no internet exception
            Log.i("msg*****", "suspend fun all_courses Response error ${e.toString()}")
            return emptyList<Course>().toMutableList<Course>()
        }
    }

    suspend fun all_courses_one_center(center_id:Int): MutableList<Course> {
        Log.i("msg*****", "all_courses_one_center")

        return try {
            response = RetrofitInstance.getRestApiServices().all_courses_one_center(center_id)
            ////////MAPEAR
            Log.i("msg*****", "Response service ${response.toString()}")
            responseMapeada =
                response.map { com.example.caira2.network.mappers.MapperImpl.toCourseModel(it) }
                        as MutableList<Course>
            Log.i("msg*****", "ResponseMapeada ${responseMapeada.toString()}")
            return responseMapeada

        } catch (e: Exception) {
//            //handles no internet exception
            Log.i("msg*****", "suspend fun all_courses Response error ${e.toString()}")
            return emptyList<Course>().toMutableList<Course>()
        }
    }
}