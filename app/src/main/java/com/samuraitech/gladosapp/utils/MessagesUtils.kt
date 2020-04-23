package com.samuraitech.gladosapp.utils

import android.util.Log
import com.google.gson.Gson
import com.samuraitech.gladosapp.model.Message

object MessagesUtils {
    fun messageToJSON(message: Message.Body) : String? {
        return try {
            val json = Gson().toJson(message)
            Log.d("MESSAGE_TO_JSON", json)
            json
        } catch (exception: Exception) {
            Log.e("ERROR", "$exception")
            null
        }
    }

    fun jsonToMessage() {

    }
}
