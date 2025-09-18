package com.example.practicanavegacion2.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.practicanavegacion2.NavScreens
import com.example.practicanavegacion2.ui.theme.PracticaNavegacion2Theme

@Composable
fun FormScreen(navController: NavController = rememberNavController()) {
    val username = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val validUsers = mapOf(
        "admin" to "admin",
        "user1" to "password1",
        "user2" to "password2"
    )
    val context = LocalContext.current
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it },
                label = { Text("Usuario") },
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth()
            )
            Button(
                onClick = {
                    if (validUsers[username.value] == password.value) {
                        navController.navigate(NavScreens.DETAIL.name)
                    } else {
                        Toast.makeText(
                            context,
                            "Error, usuario o contraseña incorrectos",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                },
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Iniciar sesión")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormScreenPreview() {
    PracticaNavegacion2Theme {
        FormScreen()
    }
}