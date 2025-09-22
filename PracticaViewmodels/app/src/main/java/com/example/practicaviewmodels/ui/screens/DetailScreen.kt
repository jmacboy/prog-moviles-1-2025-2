package com.example.practicaviewmodels.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicaviewmodels.ui.theme.PracticaViewmodelsTheme

@Composable
fun DetailScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Text(
            "Bienvenido a la pantalla de detalle",
            modifier = modifier.padding(innerPadding)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    PracticaViewmodelsTheme {
        DetailScreen()
    }
}