package com.example.practicacrudlibreria.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicacrudlibreria.presentation.models.BookUI
import com.example.practicacrudlibreria.repositories.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {


    var _bookList: MutableStateFlow<List<BookUI>> = MutableStateFlow(emptyList())
    val bookList = _bookList.asStateFlow()

    var _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    val repository = BookRepository()


    fun fetchBooks() = viewModelScope.launch {

        val res = repository.getAllBooks()
        res.fold(
            onSuccess = {data ->
                _bookList.update { data }
            },
            onFailure = {e ->
                _error.update { e.message }
            }
        )

    }

    fun deleteBook(it: BookUI) = viewModelScope.launch {
        val res = repository.deleteBook(it.id)
        res.fold(
            onSuccess = {
                fetchBooks()
            },
            onFailure = { e ->
                _error.update { e.message }
            }
        )
    }
}