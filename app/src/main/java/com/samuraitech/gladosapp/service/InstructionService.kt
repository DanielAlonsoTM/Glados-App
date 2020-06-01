package com.samuraitech.gladosapp.service

import com.samuraitech.gladosapp.model.Instruction
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface InstructionService {
    @POST("/api/instructions/insert")
    fun insertInstruction(@Body instruction: Instruction): Call<Instruction>
}
