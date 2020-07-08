package com.samuraitech.gladosapp.service

import com.samuraitech.gladosapp.model.Routine
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface RoutineService {
    @GET("/api/routines/getall")
    fun requestAllRoutines(): Call<List<Routine>>

    @PUT("/api/routines/update")
    fun updateRoutine(@Body routine: Routine): Call<Routine>
}