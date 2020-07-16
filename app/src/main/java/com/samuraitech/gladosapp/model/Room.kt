package com.samuraitech.gladosapp.model

import com.google.gson.annotations.SerializedName

data class Room(
    @SerializedName("idDocument") val idDocument: String,
    @SerializedName("idRoom") val idRoom: Int,
    @SerializedName("name") var name: String,
    @SerializedName("lastActivity") val lastActivity: String,
    @SerializedName("temperatureCelsius") val temperatureCelsius: String,
    @SerializedName("kilowattsConsumed") val kilowattsConsumed: String,
    @SerializedName("humidityPercent") val humidityPercent: String
) {
    override fun toString(): String {
        return name
    }
}
