package com.example.tcnh.data.repository

import com.example.tcnh.data.api.AuthApiService
import com.example.tcnh.data.models.LoginRequest
import com.example.tcnh.data.models.SignupRequest
import com.example.tcnh.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: AuthApiService // Define ApiService for network operations
) : AuthRepository {
    override suspend fun login(username: String, password: String): Boolean {
        return apiService.login(LoginRequest(username, password))
    }

    override suspend fun signup(username: String, password: String): Boolean {
        return apiService.signup(SignupRequest(username, password))
    }
}