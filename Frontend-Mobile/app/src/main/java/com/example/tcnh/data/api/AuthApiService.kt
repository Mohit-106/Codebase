package com.example.tcnh.data.api

import com.example.tcnh.data.models.LoginRequest
import com.example.tcnh.data.models.SignupRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Boolean

    @POST("signup")
    suspend fun signup(@Body request: SignupRequest): Boolean
}

