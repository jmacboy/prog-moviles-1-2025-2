package com.example.practicacrudlibreria.presentation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BooksScreen(){

    val viewModel: BooksViewModel = viewModel()


    Button(onClick =  {
        viewModel.fetchBooks()
    }) {
        Text("get books")
    }

}

@Composable
fun BooksContent(){

}