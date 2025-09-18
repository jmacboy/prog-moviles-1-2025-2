package com.example.practicanavegacion2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.practicanavegacion2.NavScreens
import com.example.practicanavegacion2.R
import com.example.practicanavegacion2.ui.theme.PracticaNavegacion2Theme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController = rememberNavController()) {
    Image(
        painter = painterResource(id = R.drawable.splash),
        contentDescription = "Splash Screen",
        modifier = Modifier.fillMaxSize()
    )
    LaunchedEffect(Unit) {
        delay(5000)  // the delay of 5 seconds
        navController.navigate(NavScreens.FORM.name) {
            popUpTo(NavScreens.SPLASH.name) { inclusive = true }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    PracticaNavegacion2Theme {
        SplashScreen()
    }
}