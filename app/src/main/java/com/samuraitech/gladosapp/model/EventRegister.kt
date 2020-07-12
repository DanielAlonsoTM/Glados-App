package com.samuraitech.gladosapp.model

import com.google.gson.annotations.SerializedName

data class EventRegister(
    @SerializedName("idEvent")
    val idEventRegister: String,
    @SerializedName("deviceId")
    val deviceId: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("date")
    val date: String
)
