package com.samuraitech.gladosapp.service

import com.samuraitech.gladosapp.model.Room
import retrofit2.Call
import retrofit2.http.*

interface RoomService {
    @GET("/api/rooms/getall")
    fun requestAllRooms(): Call<List<Room>>

    @POST("/api/rooms/insert")
    fun insertRoom(@Body room: Room): Call<Room>

    @PUT("/api/rooms/update")
    fun updateRoom(@Body room: Room): Call<Room>

    @DELETE("/api/rooms/delete/{roomId}")
    fun deleteRoom(@Path("roomId") roomId: String): Call<Room>
}
