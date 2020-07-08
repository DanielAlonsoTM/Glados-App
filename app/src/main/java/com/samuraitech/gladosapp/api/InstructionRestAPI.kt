package com.samuraitech.gladosapp.api

import com.samuraitech.gladosapp.model.Instruction
import com.samuraitech.gladosapp.service.InstructionService
import com.samuraitech.gladosapp.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class InstructionRestAPI {
    private val instructionService: InstructionService

    init {
        val service = Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(InstructionService::class.java)

        instructionService = service
    }

    fun insertInstruction(instruction: Instruction) : Call<Instruction> {
        return instructionService.insertInstruction(instruction)
    }
}