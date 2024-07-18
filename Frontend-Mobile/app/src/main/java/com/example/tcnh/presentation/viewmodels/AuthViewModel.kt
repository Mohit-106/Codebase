package com.example.tcnh.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcnh.domain.usecases.AuthUseCase
import com.example.tcnh.presentation.intents.AuthIntent
import com.example.tcnh.presentation.states.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state: StateFlow<AuthState> get() = _state.asStateFlow()

    private val _event = Channel<AuthIntent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    init {
        handleEvents()
    }

    private fun handleEvents() {
        viewModelScope.launch {
            event.collect { event ->
                when (event) {
                    is AuthIntent.Login -> login(event.username, event.password)
                    is AuthIntent.Signup -> signup(event.username, event.contact, event.gender, event.age, event.emergencyContact,event.password)
                }
            }
        }
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            val result = authUseCase.login(username, password)
            _state.value = if (result.isSuccess) {
                AuthState.Success("Login Successful")
            } else {
                AuthState.Error("Login Failed")
            }
        }
    }

    private fun signup(username: String, contact: String, gender: String, age: String, emergencyContact: String,password: String ) {
        Log.d("TAG", "signup: ")
        viewModelScope.launch {
            _state.value = AuthState.Loading
            val result = authUseCase.signup(username, contact, gender, age, emergencyContact,password)
            _state.value = if (result.isSuccess) {
                AuthState.Success("Signup Successful")
            } else {
                AuthState.Error(result.toString())
            }
        }
    }

    fun onEvent(event: AuthIntent) {
        viewModelScope.launch {
            _event.send(event)
        }
    }
}