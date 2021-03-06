package com.example.caira2.iu.activity.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caira2.model.Course
import com.example.caira2.repository.DashboardRepository
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    // private val _courses = MutableLiveData<MutableList<Course>>()
    private val _courses: MutableLiveData<MutableList<Course>> by lazy {
        MutableLiveData<MutableList<Course>>().also {
            llenarDatos()
        }
    }

    // val courses: LiveData<MutableList<Course>> = _courses
    fun getCourses(): LiveData<MutableList<Course>> {
        return _courses
    }

    fun llenarDatos() {
        viewModelScope.launch {
            _courses.value = DashboardRepository.all_courses()
            Log.i("msg*****", "llenarDatos() _courses ${_courses.value.toString()}")
        }
    }
}