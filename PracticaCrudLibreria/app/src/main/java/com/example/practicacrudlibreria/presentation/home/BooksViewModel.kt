package com.example.practicacrudlibreria.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicacrudlibreria.presentation.models.BookUI
import com.example.practicacrudlibreria.repositories.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {

    var _loading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val loading = _loading.asStateFlow()
    var _bookList: MutableStateFlow<List<BookUI>> = MutableStateFlow(emptyList())
    val bookList = _bookList.asStateFlow()

    var _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    val repository = BookRepository()


    fun fetchBooks() = viewModelScope.launch {
        _loading.update { true }
        val res = repository.getAllBooks()
        res.fold(
            onSuccess = { data ->
                _bookList.update { data }
                _loading.update { false }
            },
            onFailure = { e ->
                _error.update { e.message }
                _loading.update { false }
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