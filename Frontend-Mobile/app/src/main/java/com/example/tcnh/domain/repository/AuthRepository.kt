package com.example.tcnh.domain.repository

interface AuthRepository {

    suspend fun login(username: String, password: String): Result<Boolean>
    suspend fun signup(username: String, password: String): Result<Boolean>
}