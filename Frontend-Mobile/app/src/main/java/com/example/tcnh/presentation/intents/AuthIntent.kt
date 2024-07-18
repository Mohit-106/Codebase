package com.example.tcnh.presentation.intents

sealed class AuthIntent {
    data class Login(val username: String, val password: String) : AuthIntent()
    data class Signup(val username: String, val contact: String, val gender: String, val age: String, val emergencyContact: String, val password: String) : AuthIntent()
}