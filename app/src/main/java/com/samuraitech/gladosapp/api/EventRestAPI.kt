package com.samuraitech.gladosapp.api

import com.samuraitech.gladosapp.model.EventRegister
import com.samuraitech.gladosapp.service.EventService
import com.samuraitech.gladosapp.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class EventRestAPI {
    private val eventService: EventService

    init {
        val service = Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(EventService::class.java)

        eventService = service
    }

    fun getAllEvents(): Call<List<EventRegister>> {
        return eventService.requestAllEvents()
    }
}
