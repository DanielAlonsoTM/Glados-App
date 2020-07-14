package com.samuraitech.gladosapp.api

import com.samuraitech.gladosapp.model.Routine
import com.samuraitech.gladosapp.service.RoutineService
import com.samuraitech.gladosapp.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RoutineRestAPI {
    private val routineService: RoutineService

    init {
        val service = Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RoutineService::class.java)

        routineService = service
    }

    fun getAllRoutines(): Call<List<Routine>> {
        return routineService.requestAllRoutines()
    }

    fun updateRoutine(routine: Routine): Call<Routine> {
        return routineService.updateRoutine(routine)
    }

    fun deleteRoutine(routineId: String): Call<Routine> {
        return routineService.deleteRoutine(routineId)
    }
}
