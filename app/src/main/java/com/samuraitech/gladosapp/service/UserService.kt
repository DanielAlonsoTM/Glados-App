package com.samuraitech.gladosapp.service

import com.samuraitech.gladosapp.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("api/users/insert")
    fun insertUser(@Body user: User): Call<User>
}
