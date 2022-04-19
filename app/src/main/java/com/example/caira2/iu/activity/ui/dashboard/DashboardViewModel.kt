package com.example.caira2.iu.activity.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caira2.R
import com.example.caira2.model.Course
import com.example.caira2.repository.DashboardRepository
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    val coursesList= mutableListOf<Course>()

    private val _text = MutableLiveData<MutableList<Course>>()
    val text: LiveData<MutableList<Course>> = _text


    fun llenarDatos() {
        var activeProgramsList= mutableListOf<Course>()
        viewModelScope.launch {
          _text.value= DashboardRepository.all_courses()
            Log.i("msg*****", "llenarDatos() _text ${_text.value.toString()}")
       }

//        activeProgramsList.add(Course("MASTER OF COMPUTER SCIENCE","University of Upstate at Brookstone",
//            R.drawable.uni_oxford))
//        activeProgramsList.add(Course("MASTER OF MEDICINE","University of Upstate at Massachuches",
//            R.drawable.uni_masachuches))
//        activeProgramsList.add(Course("MASTER OF STACKOVERFLOW","University of Sant Google", R.drawable.uni_upv))
//        activeProgramsList.add(Course("MASTER OF MEDICINE","University of Upstate at Massachuches",
//            R.drawable.uni_masachuches))
//        activeProgramsList.add(Course("MASTER OF COMPUTER SCIENCE","University of Upstate at Brookstone",
//            R.drawable.uni_oxford))
//        activeProgramsList.add(Course("MASTER OF MEDICINE","University of Upstate at Massachuches",
//            R.drawable.uni_masachuches))
//        activeProgramsList.add(Course("MASTER OF STACKOVERFLOW","University of Sant Google", R.drawable.uni_upv))
//        activeProgramsList.add(Course("MASTER OF MEDICINE","University of Upstate at Massachuches",
//            R.drawable.uni_masachuches))
//
//        _text.value=activeProgramsList
    }
}