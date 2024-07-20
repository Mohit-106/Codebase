package com.example.tcnh.data.models

import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("name") val username: String,
    @SerializedName("phoneNumber") val contact: String,
    val gender: String,
    val age: String,
    @SerializedName("ephoneNumber") val emergencyContact: String,
    val password: String
)