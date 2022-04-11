package com.example.caira2.network.mappers

import com.example.caira2.model.User
import com.example.caira2.network.modelResponse.UserResponse

interface Mapper {
    fun toUserModel(
        userResponse: UserResponse
    ): User
}
object MapperImpl:Mapper {
    override fun toUserModel(
        userResponse: UserResponse
    ): User {
        return User(
            id = userResponse.id,
            email=userResponse.email,
            name=userResponse.name,
            password=userResponse.password,
            language1 = userResponse.language1,
            lvl_language1 = userResponse.lvl_language1,
            preferred_course1 = userResponse.preferred_course1,
            preferred_course2 = userResponse.preferred_course2,
            url_instagram = userResponse.url_instagram,
            url_linkedin = userResponse.url_linkedin,
            url_twitter = userResponse.url_twitter,
            url_web = userResponse.url_web,
            user_type = userResponse.user_type
        )
    }
//
//    fun UserResponse.toUserModel(
//        userResponse: UserResponse
//    ): User {
//        return User(
//            id = userResponse.id,
//            language1 = userResponse.language1,
//            lvl_language1 = userResponse.lvl_language1,
//            preferred_course1 = userResponse.preferred_course1,
//            preferred_course2 = userResponse.preferred_course2,
//            url_instagram = userResponse.url_instagram,
//            url_linkedin = userResponse.url_linkedin,
//            url_twitter = userResponse.url_twitter,
//            url_web = userResponse.url_web,
//            user_type = userResponse.user_type
//        )
//    }
}