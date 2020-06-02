package com.samuraitech.gladosapp.service

import com.samuraitech.gladosapp.model.Routine
import retrofit2.Call
import retrofit2.http.GET

interface RoutineService {
    @GET("/api/routines/getall")
    fun requestAllRoutines(): Call<List<Routine>>
}