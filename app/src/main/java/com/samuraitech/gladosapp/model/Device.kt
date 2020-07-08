package com.samuraitech.gladosapp.model

import com.google.gson.annotations.SerializedName

data class Device(
    @SerializedName("idDocument") val idDocument: String,
    @SerializedName("idDevice") val idDevice: String,
    @SerializedName("roomId") val roomId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String
)
