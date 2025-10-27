package com.example.practicacrudlibreria.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicacrudlibreria.presentation.models.BookUI
import com.example.practicacrudlibreria.repositories.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel: ViewModel() {
    val repository = BookRepository()

    var _book: MutableStateFlow<BookUI> = MutableStateFlow(BookUI())
    val book = _book.asStateFlow()

    fun fetchBook(bookId: Int) = viewModelScope.launch {

        val res = repository.getBookById(bookId)
        res.fold(
            onSuccess = {data ->

                _book.update {data  }
            },
            onFailure = {

            }
        )
    }
}
