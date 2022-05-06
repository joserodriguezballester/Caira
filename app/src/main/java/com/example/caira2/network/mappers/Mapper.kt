package com.example.caira2.network.mappers

import com.example.caira2.model.Center
import com.example.caira2.model.Course
import com.example.caira2.model.User
import com.example.caira2.network.modelResponse.CenterResponse
import com.example.caira2.network.modelResponse.CourseResponse
import com.example.caira2.network.modelResponse.UserResponse

interface Mapper {
    fun toUserModel(userResponse: UserResponse): User
    fun toCourseModel(courseResponse: CourseResponse): Course
    fun toCenterModel(centerResponse: CenterResponse): Center

}

object MapperImpl : Mapper {
    override fun toUserModel(userResponse: UserResponse): User {
        return User(
            id = userResponse.id,
            email = userResponse.email,
            name = userResponse.name,
            password = userResponse.password,
            language1 = userResponse.language1,
            lvl_language1 = userResponse.lvl_language1,
            preferred_course1 = userResponse.preferred_course1,
            preferred_course2 = userResponse.preferred_course2,
            url_instagram = userResponse.url_instagram,
            url_linkedin = userResponse.url_linkedin,
            url_twitter = userResponse.url_twitter,
            url_web = userResponse.url_web,
            user_type = userResponse.user_type,
            logo = userResponse.logo,
            banner = userResponse.banner
        )
    }

    override fun toCourseModel(courseResponse: CourseResponse): Course {
        val center = Center(
            id = courseResponse.center.id,
            acronym = courseResponse.center.acronym,
            type_center = courseResponse.center.type_center,
            name = courseResponse.center.name,
            email = courseResponse.center.email,
            location = courseResponse.center.location,
            logo = courseResponse.center.logo,
            banner = courseResponse.center.banner,
            description = courseResponse.center.description,
            website = courseResponse.center.website,
            imgUrl =""
        )
        return Course(
            category = courseResponse.category,
            center_id = courseResponse.center_id,
            duration = courseResponse.duration,
            id = courseResponse.id,
            language = courseResponse.language,
            modality = courseResponse.modality,
            name = courseResponse.name,
            price = courseResponse.price,
            price_enrollment = courseResponse.price_enrollment,
            scholarship = courseResponse.scholarship,
            vacancies = courseResponse.vacancies,
            vacancies_available = courseResponse.vacancies_available,
            description = courseResponse.description,
            location = courseResponse.location,
            image = courseResponse.image,
            center = center
        )
    }

    override fun toCenterModel(centerResponse: CenterResponse): Center {
        return Center(
            acronym = centerResponse.acronym,
            email = centerResponse.email,
            location = centerResponse.location,
            name = centerResponse.name,
            type_center = centerResponse.type_center,
            website = centerResponse.website,
            id = centerResponse.id,
            logo = centerResponse.logo,
            banner = centerResponse.banner,
            description = centerResponse.description,
            imgUrl = "https://apicaira.lunarxy.com" + centerResponse.logo?.substring(1)
        )
    }
}