package com.example.tcnh.data.api

import com.example.tcnh.data.models.LoginRequest
import com.example.tcnh.data.models.SignupRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("authenticate")
    suspend fun login(@Body request: LoginRequest): Result<Boolean>

    @POST("do-register")
    suspend fun signup(@Body request: SignupRequest): Result<Boolean>
}

