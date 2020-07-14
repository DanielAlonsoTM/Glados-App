package com.samuraitech.gladosapp.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("idDocument") val idDocument: String,
    @SerializedName("idUser") val idUser: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String
)
