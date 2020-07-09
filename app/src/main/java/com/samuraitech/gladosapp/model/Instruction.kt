package com.samuraitech.gladosapp.model

import com.google.gson.annotations.SerializedName

data class Instruction(
    @SerializedName("idDocument") val idDocument: String,
    @SerializedName("idInstruction") val idInstruction: String,
    @SerializedName("date") val date: String,
    @SerializedName("contentInstruction") val contentInstruction: ContentInstruction,
    @SerializedName("executed") val executed: Int
)

data class ContentInstruction(
    @SerializedName("deviceId") val deviceId: String,
    @SerializedName("roomId") val roomId: Int,
    @SerializedName("action") val action: EnumType.ActionType,
    @SerializedName("timeToExecute") val timeToExecute: Long
)