package com.samuraitech.gladosapp.service

import com.samuraitech.gladosapp.model.Routine
import retrofit2.Call
import retrofit2.http.*

interface RoutineService {
    @GET("/api/routines/getall")
    fun requestAllRoutines(): Call<List<Routine>>

    @PUT("/api/routines/update")
    fun updateRoutine(@Body routine: Routine): Call<Routine>

    @DELETE("/api/routines/delete/{routineId}")
    fun deleteRoutine(@Path("routineId") routineId: String): Call<Routine>
}