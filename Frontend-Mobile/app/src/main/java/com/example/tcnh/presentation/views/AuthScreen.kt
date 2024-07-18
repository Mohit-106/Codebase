package com.example.tcnh.presentation.views

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tcnh.R
import com.example.tcnh.presentation.intents.AuthIntent
import com.example.tcnh.presentation.states.AuthState
import com.example.tcnh.presentation.viewmodels.AuthViewModel
import com.example.tcnh.ui.theme.CustomFontFamily
import com.example.tcnh.utils.GenderSwitch

@Composable
fun AuthScreen(viewModel: AuthViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isMale by remember { mutableStateOf(true) }
    var age by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 36.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Welcome to TCNH",
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 32.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Please provide all the required information",
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(72.dp))

        Column(
            verticalArrangement = Arrangement.Center
        ) {

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = { username = it },
                label = { Text("Name") }
            )
            Spacer(modifier = Modifier.height(22.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = { username = it },
                label = { Text("Contact") }
            )
            Spacer(modifier = Modifier.height(22.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Row(
                    modifier = Modifier.weight(2f), verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "FEMALE", fontFamily = CustomFontFamily,
                        fontSize = 13.sp,
                        fontWeight = if (!isMale) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        }
                    )
                    Switch(
                        checked = isMale,
                        onCheckedChange = { isMale = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.Black,
                            checkedTrackColor = Color.LightGray,
                            uncheckedThumbColor = Color.Black,
                            uncheckedTrackColor = Color.LightGray,
                        ),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clip(RoundedCornerShape(1.dp))
                    )
                    Text(
                        text = "MALE", fontFamily = CustomFontFamily,
                        fontSize = 13.sp,
                        fontWeight = if (isMale) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        }
                    )
                }
                Spacer(modifier = Modifier.weight(0.5f))
                TextField(
                    modifier = Modifier
                        .wrapContentWidth()
                        .weight(1.5f),
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Spacer(modifier = Modifier.height(22.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = { username = it },
                label = { Text("Emergency Contact") }
            )
            Spacer(modifier = Modifier.height(22.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(50.dp))
//        Button(modifier = Modifier.fillMaxWidth(), onClick = { viewModel.onEvent(AuthIntent.Login(username, password)) }) {
//            Text("Login")
//        }
//        Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Black),
                onClick = { viewModel.onEvent(AuthIntent.Signup(username, password)) }) {
                Text(
                    "Signup",
                    fontFamily = CustomFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account?",
                    fontFamily = CustomFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }

        }


//        when (state) {
//            is AuthState.Idle -> Text("Idle")
//            is AuthState.Loading -> CircularProgressIndicator()
//            is AuthState.Success -> Text((state as AuthState.Success).message)
//            is AuthState.Error -> Text(
//                (state as AuthState.Error).message,
//                color = MaterialTheme.colorScheme.error
//            )
//        }
    }


}