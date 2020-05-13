package com.samuraitech.gladosapp.model

import android.graphics.drawable.Drawable

data class EventRegister(
    val header: String,
    val content: String,
    val type: EnumType.EventType,
    val drawable: Drawable
)
