package com.samuraitech.gladosapp.api

import com.samuraitech.gladosapp.model.User
import com.samuraitech.gladosapp.service.UserService
import com.samuraitech.gladosapp.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class UserRestAPI {
    private val userService: UserService

    init {
        val service = Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UserService::class.java)

        userService = service
    }

    fun insertUser(user: User): Call<User> {
        return userService.insertUser(user)
    }
}
