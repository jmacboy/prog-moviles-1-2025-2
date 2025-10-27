package com.example.practicacrudlibreria.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun BookDetailScreen(
    bookId: Int,
    onBack: () -> Unit,
) {

    val viewModel: BookDetailViewModel = viewModel()
    val item by viewModel.book.collectAsState()

    Scaffold { paddingValues ->

        LaunchedEffect(bookId) {

            viewModel.fetchBook(bookId)
        }

        Box(modifier = Modifier.padding(paddingValues)) {

            Card(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Column(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = item.imagen,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.padding(8.dp).width(120.dp).height(200.dp)
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
                            fontSize = 12.sp
                        )
                        Text(item.editorial)
                        Row {
                            item.generos.forEach {
                                Text(it)
                            }
                        }

                    }
                }


            }

        }
    }
    // Implementación de la pantalla de detalle del libro
}