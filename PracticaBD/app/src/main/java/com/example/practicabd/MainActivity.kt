package com.example.practicabd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practicabd.ui.screens.DetailScreen
import com.example.practicabd.ui.screens.HomeScreen
import com.example.practicabd.ui.screens.NavScreens
import com.example.practicabd.ui.theme.PracticaBDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaBDTheme {
                NavigationApp()
            }
        }
    }
}

@Composable
fun NavigationApp(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.HOME.name
    ) {
        composable(NavScreens.HOME.name) {
            HomeScreen(navController)
        }

        composable(
            route = "${NavScreens.DETAIL.name}/{personId}",
            arguments = listOf(
                navArgument("personId") { type = NavType.IntType }
            )
        ) {
            val personId = it.arguments?.getInt("personId") ?: -1
            DetailScreen(navController, personId)
        }
        composable(NavScreens.DETAIL.name) {
            DetailScreen(navController)
        }
    }
}
