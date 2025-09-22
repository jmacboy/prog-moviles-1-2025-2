package com.example.practicaviewmodels.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.practicaviewmodels.ui.theme.PracticaViewmodelsTheme


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navConroller: NavController = rememberNavController()
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column {
            Text(
                "Home screen",
                modifier = modifier.padding(innerPadding)
            )
            Button(
                onClick = {
                    navConroller.navigate(NavScreens.DETAIL.name)
                }
            ) {
                Text("Ir a la pantalla de detalle")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PracticaViewmodelsTheme {
        HomeScreen()
    }
}