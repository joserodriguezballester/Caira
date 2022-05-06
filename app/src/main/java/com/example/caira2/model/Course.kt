package com.example.caira2.model

import java.io.Serializable

data class Course(
    val category: String,
    val center_id: Int,
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
    val description: String,
    val location: String,
    val image: String,
    val center: Center
) : Serializable
