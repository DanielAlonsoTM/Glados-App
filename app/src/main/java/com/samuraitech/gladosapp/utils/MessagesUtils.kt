package com.samuraitech.gladosapp.utils

import android.util.Log
import com.google.gson.Gson
import com.samuraitech.gladosapp.model.Message

object MessagesUtils {
    fun messageToJSONFormat(message: Message.Body): String? {
        return try {
            val json = Gson().toJson(message, Message.Body::class.java)

            json
        } catch (exception: Exception) {
            Log.e("ERROR", "$exception")
            null
        }
    }
}
