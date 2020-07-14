package com.samuraitech.gladosapp.api

import com.samuraitech.gladosapp.model.Room
import com.samuraitech.gladosapp.service.RoomService
import com.samuraitech.gladosapp.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RoomRestAPI() {
    private val roomService: RoomService

    init {
        val service = Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RoomService::class.java)

        roomService = service
    }

    fun getAllRooms(): Call<List<Room>> {
        return roomService.requestAllRooms()
    }

    fun updateRoom(room: Room): Call<Room> {
        return roomService.updateRoom(room)
    }

    fun deleteRoom(idRoom: String): Call<Room> {
        return roomService.deleteRoom(idRoom)
    }
}
