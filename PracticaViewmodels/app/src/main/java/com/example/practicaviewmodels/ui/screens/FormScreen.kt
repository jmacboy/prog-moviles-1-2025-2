package com.example.practicaviewmodels.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.practicaviewmodels.ui.theme.PracticaViewmodelsTheme
import com.example.practicaviewmodels.ui.viewmodels.FormScreenViewModel

@Composable
fun FormScreen(
    navController: NavController = rememberNavController(),
    vm: FormScreenViewModel = viewModel()
) {
    val context = LocalContext.current
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = vm.username.value,
                onValueChange = { vm.username.value = it },
                label = { Text("Usuario") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = vm.password.value,
                onValueChange = { vm.password.value = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            Button(
                onClick = {
                    vm.checkUser()
                    if (vm.isUserValid.value) {
                        navController.navigate(NavScreens.DETAIL.name)
                    } else {
                        Toast.makeText(
                            context,
                            "Usuario o contraseña incorrectos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
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
    PracticaViewmodelsTheme {
        FormScreen()
    }
}