package com.example.tcnh.data.repository

import android.util.Log
import com.example.tcnh.data.api.AuthApiService
import com.example.tcnh.data.models.SignupRequest
import com.example.tcnh.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    // Add necessary dependencies, e.g., network service or data source
    val authApiService: AuthApiService
) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<Boolean> {
        // Implement login logic
        return Result.success(true)
    }

    override suspend fun signup(username: String, contact: String, gender: String, age: String, emergencyContact: String,password: String): Result<Boolean> {
        // Implement signup logic

        Log.d("Repo check", "signup: $username $contact $gender $age $emergencyContact")

        return authApiService.signup(SignupRequest(username,contact,gender,age,emergencyContact,password))
    }
}