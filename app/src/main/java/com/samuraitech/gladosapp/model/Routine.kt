package com.samuraitech.gladosapp.model

import com.google.gson.annotations.SerializedName

data class Routine(
    @SerializedName("idRoutine")
    val idRoutine: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("deviceId")
    val deviceId: String,
    @SerializedName("roomId")
    val roomId: Int,
    @SerializedName("action")
    val action: Int,
    @SerializedName("active")
    val active: Int,
    @SerializedName("timeInit")
    val timeInit: String
)