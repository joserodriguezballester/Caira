package com.example.caira2.iu.activity.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caira2.model.Course
import com.example.caira2.repository.DashboardRepository
import kotlinx.coroutines.launch

class DetailCourseViewModel : ViewModel() {

    private val _courses = MutableLiveData<MutableList<Course>>()
    val courses: LiveData<MutableList<Course>> = _courses

    fun llenarDatos() {
        viewModelScope.launch {
            _courses.value = DashboardRepository.all_courses()
            Log.i("msg*****", "llenarDatos()  ${_courses.value.toString()}")
        }
    }

    fun llenarDatos(centerId: Int) {
        Log.i("msg*****", "llenarDatos(centerId: Int)  ${centerId}")

        viewModelScope.launch {
            _courses.value = DashboardRepository.all_courses_one_center(centerId)
            Log.i("msg*****", "llenarDatos(centerId: Int)  ${_courses.value.toString()}")
        }
    }
}