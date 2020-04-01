package com.samuraitech.gladosapp.model

data class Notification(val header: String, val content: String, val type: NotificationType)

enum class NotificationType {
    NORMAL, WARNING, ERROR
}