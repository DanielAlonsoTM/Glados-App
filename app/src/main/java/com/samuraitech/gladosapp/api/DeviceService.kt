package com.samuraitech.gladosapp.api

import com.samuraitech.gladosapp.model.Device
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DeviceService {
    @GET("/api/devices/getall")
    fun requestAllDevices(): Call<List<Device>>

    @GET("/api/devices/byRoomId/{roomId}")
    fun requestByRoomId(@Path("roomId") roomId: Int): Call<List<Device>>
}