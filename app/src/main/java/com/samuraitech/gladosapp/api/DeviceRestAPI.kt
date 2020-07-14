package com.samuraitech.gladosapp.api

import com.samuraitech.gladosapp.model.Device
import com.samuraitech.gladosapp.service.DeviceService
import com.samuraitech.gladosapp.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DeviceRestAPI {
    private val deviceService: DeviceService

    init {
        val service = Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
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

    fun updateDevice(device: Device): Call<Device> {
        return deviceService.updateDevice(device)
    }

    fun deleteDevice(device: Device): Call<Device> {
        return deviceService.deleteDevice(device.idDocument)
    }
}

