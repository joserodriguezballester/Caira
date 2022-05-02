package com.example.caira2.network.modelResponse

data class CourseResponse(
    val category: String,
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
    val description: String,
    val location: String,
    val image: String,
    val center: CenterResponse

)