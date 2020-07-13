package com.samuraitech.gladosapp.model

import com.google.gson.annotations.SerializedName

data class Device(
    @SerializedName("idDocument") val idDocument: String,
    @SerializedName("idDevice") val idDevice: String,
    @SerializedName("roomId") var roomId: Int,
    @SerializedName("name") var name: String,
    @SerializedName("type") var type: String
)
