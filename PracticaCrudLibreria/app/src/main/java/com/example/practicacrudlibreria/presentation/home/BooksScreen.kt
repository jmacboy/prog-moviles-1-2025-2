package com.example.practicacrudlibreria.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.practicacrudlibreria.presentation.models.BookUI
import com.example.practicacrudlibreria.ui.theme.PracticaCrudLibreriaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(
    viewModel: BooksViewModel,
    onItemTap: (BookUI) -> Unit,
    onTapAdd: () -> Unit,
    onEdit: (BookUI) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Books Library") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onTapAdd() },
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            BooksContent(viewModel, onItemTap, onEdit)
        }
    }


}

@Composable
fun BooksContent(viewModel: BooksViewModel, onItemTap: (BookUI) -> Unit, onEdit: (BookUI) -> Unit) {

    LaunchedEffect(Unit) {
        viewModel.fetchBooks()
    }
    val books by viewModel.bookList.collectAsState()
    val loading by viewModel.loading.collectAsState()
    if (loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {
            items(books.size) { index ->
                BookItem(item = books[index], onItemTap, onDelete = {
                    viewModel.deleteBook(it)
                }, onEdit = {
                    onEdit(it)
                })
            }
        }
    }
}


@Composable
fun BookItem(
    item: BookUI,
    onItemTap: (BookUI) -> Unit = {},
    onDelete: (BookUI) -> Unit,
    onEdit: (BookUI) -> Unit = {}
) {

    Card(
        onClick = { onItemTap(item) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = item.imagen,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(8.dp)
                    .width(120.dp)
                    .height(200.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Text(item.nombre.uppercase(), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(item.autor.uppercase(), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(
                    item.sinopsis,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp
                )

                Row {
                    IconButton(onClick = { onDelete(item) }) {
                        Icon(Icons.Default.Delete, "")
                    }

                    IconButton(onClick = { onEdit(item) }) {
                        Icon(Icons.Default.Edit, "")
                    }

                }

            }
        }


    }
}

@PreviewLightDark
@Composable
fun BookItemPreview() {
    PracticaCrudLibreriaTheme {


        val book = BookUI(
            id = 1,
            autor = " Gabriel Garcia Marquez",
            calificacion = 5,
            editorial = "Editorial Sudamericana",
            generos = listOf("1,", "2")
        )
        Surface {

            BookItem(book, onDelete = {})
        }
    }
}