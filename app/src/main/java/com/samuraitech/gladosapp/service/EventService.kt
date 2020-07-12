package com.samuraitech.gladosapp.service

import com.samuraitech.gladosapp.model.EventRegister
import retrofit2.Call
import retrofit2.http.GET

interface EventService {
    @GET("/api/events/getall")
    fun requestAllEvents(): Call<List<EventRegister>>
}
