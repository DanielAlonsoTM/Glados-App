package com.samuraitech.gladosapp.model

import com.google.gson.annotations.SerializedName

class Message {
    data class Body(
        @SerializedName("id") val id: String,
        @SerializedName("date") val date: String,
        @SerializedName("type") val type: MessageType,
        @SerializedName("contentType") var contentType: Any
    )

    enum class MessageType {
        INSTRUCTION, EVENT
    }

    data class Event(
        @SerializedName("id_device") val id_device: String,
        @SerializedName("id_room") val id_room: Int,
        @SerializedName("type") val type: EnumType.EventType,
        @SerializedName("title") val title: String,
        @SerializedName("content") val content: String
    )
}
