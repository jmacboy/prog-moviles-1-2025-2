package com.example.practicaretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practicaretrofit.presentation.PostDetailScreen
import com.example.practicaretrofit.presentation.PostListScreen
import com.example.practicaretrofit.presentation.PostViewModel
import com.example.practicaretrofit.ui.theme.PracticaRetrofitTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            PracticaRetrofitTheme {
                // A surface container using the 'background' color from the theme.
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "PostListScreen"
                ) {
                    composable("PostListScreen") {
                        PostListScreen(onItemTap = { postUI ->
                            val id = postUI.id
                            navController.navigate("postDetail/$id")
                        })
                    }

                    composable(
                        "postDetail/{postId}",
                        arguments = listOf(navArgument("postId") { type = NavType.IntType })
                    ) { back ->
                        val postId = back.arguments?.getInt("postId") ?: -1
                        PostDetailScreen(postId, onBack =  {
                            navController.popBackStack()
                        })
                    }
                }

            }


        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticaRetrofitTheme {
        Greeting("Android")
    }
}