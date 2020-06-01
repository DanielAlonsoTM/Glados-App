package com.samuraitech.gladosapp.api

import com.samuraitech.gladosapp.model.Device
import com.samuraitech.gladosapp.service.DeviceService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DeviceRestAPI {
    private val deviceService: DeviceService

    init {
        val service = Retrofit.Builder()
            .baseUrl("http://192.168.1.88:8080")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(DeviceService::class.java)

        deviceService = service
    }

    fun getAllDevices(): Call<List<Device>> {
        return deviceService.requestAllDevices()
    }

    fun getByRoomId(roomId: Int): Call<List<Device>> {
        return deviceService.requestByRoomId(roomId)
    }
}

