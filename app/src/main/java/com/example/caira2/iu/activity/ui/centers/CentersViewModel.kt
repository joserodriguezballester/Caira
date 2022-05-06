package com.example.caira2.iu.activity.ui.centers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caira2.model.Center
import com.example.caira2.repository.CentersRepository
import kotlinx.coroutines.launch

class CentersViewModel : ViewModel() {

    private val _centers = MutableLiveData<List<Center>>()
    val centers: LiveData<List<Center>> = _centers
    lateinit var imgUrl: String
    fun getCenters() {
        viewModelScope.launch {
            _centers.value = CentersRepository.all_centers()
            Log.i("msg*****", "llenarDatos:: ${_centers.value.toString()}")
        }
    }
}