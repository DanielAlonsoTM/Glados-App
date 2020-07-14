package com.samuraitech.gladosapp.model

import com.google.gson.annotations.SerializedName

data class Routine(
    @SerializedName("idDocument") val idDocument: String,
    @SerializedName("idRoutine") val idRoutine: String,
    @SerializedName("deviceId") val deviceId: String,
    @SerializedName("name") val name: String,
    @SerializedName("action") val action: Int,
    @SerializedName("precision") val precision: Float,
    @SerializedName("timeInit") val timeInit: String,
    @SerializedName("active") var active: Int,
    @SerializedName("roomId") val roomId: Int,
    @SerializedName("userId") val userId:String
)