package com.samuraitech.gladosapp.api

import com.samuraitech.gladosapp.model.Room
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RoomRestAPI() {
    private val roomService: RoomService

    init {
        val service = Retrofit.Builder()
            .baseUrl("http://192.168.1.88:8080")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RoomService::class.java)

        roomService = service
    }

    fun getAllRooms(): Call<List<Room>> {
        return roomService.requestAllRooms()
    }
}
