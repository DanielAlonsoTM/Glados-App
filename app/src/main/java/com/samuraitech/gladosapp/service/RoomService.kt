package com.samuraitech.gladosapp.service

import com.samuraitech.gladosapp.model.Room
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface RoomService {
    @GET("/api/rooms/getall")
    fun requestAllRooms() : Call<List<Room>>

    @PUT("/api/rooms/update")
    fun updateRoom(@Body room: Room): Call<Room>
}
