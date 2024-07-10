package com.example.tcnh.domain.usecases

interface AuthUseCase {

    suspend fun login(username: String, password: String): Result<Boolean>
    suspend fun signup(username: String, password: String): Result<Boolean>

}