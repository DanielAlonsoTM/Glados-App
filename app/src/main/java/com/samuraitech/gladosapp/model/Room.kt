package com.samuraitech.gladosapp.model

data class Room(
    val id: Int,
    val name: String,
    val lastActivity: String,
    val temperature: Int,
    val kilowattsConsumed: Int,
    val humidity: Int,
    val devicesInRoom: ArrayList<Device>
)
