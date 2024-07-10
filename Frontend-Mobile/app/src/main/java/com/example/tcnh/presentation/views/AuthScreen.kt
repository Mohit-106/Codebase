package com.example.tcnh.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tcnh.presentation.intents.AuthIntent
import com.example.tcnh.presentation.states.AuthState
import com.example.tcnh.presentation.viewmodels.AuthViewModel

@Composable
fun AuthScreen(viewModel: AuthViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.onEvent(AuthIntent.Login(username, password)) }) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.onEvent(AuthIntent.Signup(username, password)) }) {
            Text("Signup")
        }
        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            is AuthState.Idle -> Text("Idle")
            is AuthState.Loading -> CircularProgressIndicator()
            is AuthState.Success -> Text((state as AuthState.Success).message)
            is AuthState.Error -> Text((state as AuthState.Error).message, color = MaterialTheme.colorScheme.error)
        }
    }



}