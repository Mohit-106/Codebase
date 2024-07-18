package com.example.tcnh.data.usecases

import com.example.tcnh.domain.repository.AuthRepository
import com.example.tcnh.domain.usecases.AuthUseCase
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : AuthUseCase {

    override suspend fun login(username: String, password: String): Result<Boolean> {
        return authRepository.login(username, password)
    }

    override suspend fun signup(username: String, password: String): Result<Boolean> {
        return authRepository.signup(username, password)
    }
}