package com.example.practicacrudlibreria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practicacrudlibreria.presentation.detail.BookDetailScreen
import com.example.practicacrudlibreria.presentation.form.BookFormScreen
import com.example.practicacrudlibreria.presentation.home.BooksScreen
import com.example.practicacrudlibreria.presentation.home.BooksViewModel
import com.example.practicacrudlibreria.ui.theme.PracticaCrudLibreriaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            PracticaCrudLibreriaTheme {

                val navController = rememberNavController()
                val viewModel: BooksViewModel = viewModel()


                NavHost(
                    navController = navController,
                    startDestination = "BooksListScreen"
                ) {
                    composable("BooksListScreen") {

                        BooksScreen(viewModel = viewModel, onItemTap = { book ->
                            val id = book.id
                            navController.navigate("bookDetail/$id")
                        }, onTapAdd = {
                            val id = -1
                            navController.navigate("bookForm/$id")
                        }, onEdit = { bookUI ->
                            val id = bookUI.id
                            navController.navigate("bookForm/$id")
                        })

                    }

                    composable(
                        "bookDetail/{bookId}",
                        arguments = listOf(navArgument("bookId") { type = NavType.IntType })
                    ) { back ->
                        val bookId = back.arguments?.getInt("bookId") ?: -1
                        BookDetailScreen(bookId, onBack = {
                            navController.popBackStack()
                        })
                    }

                    composable(
                        "bookForm/{bookId}",
                        arguments = listOf(navArgument("bookId") { type = NavType.IntType })
                    ) { back ->
                        val bookId = back.arguments?.getInt("bookId") ?: -1
                        BookFormScreen(
                            bookId
                        ) {
                            navController.popBackStack()
                            viewModel.fetchBooks()
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
    PracticaCrudLibreriaTheme {
        Greeting("Android")
    }
}