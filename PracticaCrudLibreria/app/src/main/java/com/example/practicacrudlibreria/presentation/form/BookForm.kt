package com.example.practicacrudlibreria.presentation.form

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.sin


@Composable
fun BookFormScreen(bookId: Int, onSuccess: () -> Unit) {
    val viewModel: BookFormViewModel = viewModel()

    LaunchedEffect(bookId) {
        if (bookId != -1) {
            viewModel.fetchBook(bookId)
        }
    }


    val bookDto by viewModel.bookFormUI.collectAsState()


    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

            Column(Modifier.verticalScroll(rememberScrollState())) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = bookDto.nombre,
                    onValueChange = { value ->
                        viewModel.onNameChange(value)
                    },
                    label = { androidx.compose.material3.Text(text = "Title") }
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = bookDto.sinopsis,
                    onValueChange = { value ->
                        viewModel.onSinopsisChange(value)
                    },
                    label = { androidx.compose.material3.Text(text = "Sinopsis") }
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = bookDto.autor,
                    onValueChange = { value ->
                        viewModel.onAutorChange(value)
                    },
                    label = { androidx.compose.material3.Text(text = "autor") }
                )


                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = bookDto.editorial,
                    onValueChange = { value ->
                        viewModel.onEditorialChange(value)
                    },
                    label = { androidx.compose.material3.Text(text = "editorial") }
                )

                Button(onClick = {
                    viewModel.submitBook(bookId,  onSuccess = {
                        onSuccess()
                    })
                }) {
                    Text("Submit")
                }
            }

        }
    }

}