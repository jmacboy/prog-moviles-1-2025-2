package com.example.practicacrudlibreria.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicacrudlibreria.repositories.BookRepository
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {

    val repository = BookRepository()


    fun fetchBooks() = viewModelScope.launch {
        repository.getAllBooks()
    }
}