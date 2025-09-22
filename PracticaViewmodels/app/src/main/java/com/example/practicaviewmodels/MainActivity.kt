package com.example.practicaviewmodels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicaviewmodels.ui.screens.DetailScreen
import com.example.practicaviewmodels.ui.screens.FormScreen
import com.example.practicaviewmodels.ui.screens.HomeScreen
import com.example.practicaviewmodels.ui.screens.SplashScreen
import com.example.practicaviewmodels.ui.screens.NavScreens
import com.example.practicaviewmodels.ui.theme.PracticaViewmodelsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaViewmodelsTheme {
                NavigationApp()

            }
        }
    }
}

@Composable
fun NavigationApp(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.SPLASH.name
    ) {
        composable(NavScreens.HOME.name) {
            HomeScreen(modifier = Modifier, navController)
        }
        composable(NavScreens.DETAIL.name) {
            DetailScreen()
        }
        composable(NavScreens.FORM.name) {
            FormScreen(navController)
        }
        composable(NavScreens.SPLASH.name) {
            SplashScreen(navController)
        }
    }
}



