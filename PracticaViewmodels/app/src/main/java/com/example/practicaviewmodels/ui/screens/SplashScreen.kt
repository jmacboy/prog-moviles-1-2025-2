package com.example.practicaviewmodels.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import com.example.practicaviewmodels.R
import com.example.practicaviewmodels.ui.theme.PracticaViewmodelsTheme
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
    PracticaViewmodelsTheme {
        SplashScreen()
    }
}