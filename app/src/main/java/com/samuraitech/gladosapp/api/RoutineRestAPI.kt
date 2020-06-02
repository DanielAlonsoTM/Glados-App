package com.samuraitech.gladosapp.api

import com.samuraitech.gladosapp.model.Routine
import com.samuraitech.gladosapp.service.RoutineService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RoutineRestAPI {
    private val routineService: RoutineService

    init {
        val service = Retrofit.Builder()
            .baseUrl("http://192.168.1.88:8080")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RoutineService::class.java)

        routineService = service
    }

    fun getAllRoutines(): Call<List<Routine>> {
        return routineService.requestAllRoutines()
    }
}
