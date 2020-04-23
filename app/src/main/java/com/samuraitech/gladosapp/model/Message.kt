package com.samuraitech.gladosapp.model

import java.time.LocalDateTime

class Message {
    data class Body(
        val id: String,
        val date: String,
        val type: MessageType,
        var contentType: Any
    )

    enum class MessageType {
        INSTRUCTION, EVENT
    }

    data class Event(
        val id_device: String,
        val id_room: Int,
        val type: EventType,
        val title: String,
        val content: String
    )

    data class Instruction(
        val id_device: String,
        val id_room: Int,
        val action: String,
        val timeToExecute: Long
    )
}


