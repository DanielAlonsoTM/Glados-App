package com.samuraitech.gladosapp.model

import android.graphics.drawable.Drawable

data class Notification(val header: String, val content: String, val type: NotificationType, val drawable: Drawable)

enum class NotificationType {
    NORMAL, WARNING, ERROR
}