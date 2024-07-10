package com.example.tcnh.domain.repository

interface AuthRepository {

    suspend fun login(username: String, password: String): Boolean
    suspend fun signup(username: String, password: String): Boolean

}