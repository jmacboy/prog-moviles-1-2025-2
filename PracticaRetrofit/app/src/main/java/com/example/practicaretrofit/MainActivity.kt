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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        val viewModel: PostViewModel = viewModel()
                        val list by viewModel.list.collectAsState()
                        val isLoading by viewModel.isLoading.collectAsState()
                        val errorText by viewModel.error.collectAsState()

                        PostListScreen(
                            list = list,
                            isLoading = isLoading,
                            onRefresh = {
                                viewModel.fetchPosts()
                            })

                        if (errorText != null) {
                            AlertDialog(onDismissRequest = {}, confirmButton = {
                                Text(
                                    "OK", modifier = Modifier
                                        .padding(8.dp)
                                        .clickable {
                                            viewModel.dismissError()
                                        })
                            }, title = {
                                Text("Error")
                            }, text = {
                                Text(errorText ?: "Unknown error")
                            })

                        }

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