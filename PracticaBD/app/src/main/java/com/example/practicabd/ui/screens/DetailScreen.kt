package com.example.practicabd.ui.screens

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicabd.ui.theme.PracticaBDTheme
import com.example.practicabd.ui.viewmodels.DetailViewModel

@Composable
fun DetailScreen(
    navController: NavHostController = rememberNavController(),
    vm: DetailViewModel = viewModel()
) {
    val context = LocalContext.current
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = vm.name.value,
                onValueChange = { vm.name.value = it },
                label = { Text("Nombre") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = vm.lastName.value,
                onValueChange = { vm.lastName.value = it },
                label = { Text("Apellido") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = vm.age.value,
                onValueChange = { vm.age.value = it },
                label = { Text("Edad") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = vm.city.value,
                onValueChange = { vm.city.value = it },
                label = { Text("Ciudad") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = vm.birthDate.value,
                onValueChange = { vm.birthDate.value = it },
                label = { Text("Fecha de Nacimiento") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            Button(
                onClick = {
                    vm.savePerson(context)
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(vertical = 0.dp, horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text("Guardar")
            }
            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(vertical = 0.dp, horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text("Cancelar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    PracticaBDTheme {
        DetailScreen()
    }
}