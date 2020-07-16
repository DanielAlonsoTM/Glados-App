package com.samuraitech.gladosapp.service

import com.samuraitech.gladosapp.model.Device
import retrofit2.Call
import retrofit2.http.*

interface DeviceService {
    @GET("/api/devices/getall")
    fun requestAllDevices(): Call<List<Device>>

    @GET("/api/devices/byRoomId/{roomId}")
    fun requestByRoomId(@Path("roomId") roomId: Int): Call<List<Device>>

    @POST("/api/devices/insert")
    fun insertDevice(@Body device: Device): Call<Device>

    @PUT("api/devices/update")
    fun updateDevice(@Body device: Device): Call<Device>

    @DELETE("api/devices/delete/{deviceId}")
    fun deleteDevice(@Path("deviceId") deviceId: String): Call<Device>
}