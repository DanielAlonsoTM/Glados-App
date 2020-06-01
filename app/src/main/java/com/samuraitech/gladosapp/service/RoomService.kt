package com.samuraitech.gladosapp.service

import com.samuraitech.gladosapp.model.Room
import retrofit2.Call
import retrofit2.http.GET

interface RoomService {
    @GET("/api/rooms/getall")
    fun requestAllRooms() : Call<List<Room>>
}
