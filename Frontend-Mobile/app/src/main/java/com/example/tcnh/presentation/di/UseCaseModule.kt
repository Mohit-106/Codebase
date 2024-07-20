package com.example.tcnh.presentation.di

import com.example.tcnh.data.repository.AuthRepositoryImpl
import com.example.tcnh.data.usecases.AuthUseCaseImpl
import com.example.tcnh.domain.repository.AuthRepository
import com.example.tcnh.domain.usecases.AuthUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindAuthUseCase(
        authUseCaseImpl: AuthUseCaseImpl
    ): AuthUseCase
}