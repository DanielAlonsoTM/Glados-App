package com.samuraitech.gladosapp.service

import com.samuraitech.gladosapp.model.Device
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface DeviceService {
    @GET("/api/devices/getall")
    fun requestAllDevices(): Call<List<Device>>

    @GET("/api/devices/byRoomId/{roomId}")
    fun requestByRoomId(@Path("roomId") roomId: Int): Call<List<Device>>

    @PUT("api/devices/update")
    fun updateDevice(@Body device: Device): Call<Device>
}