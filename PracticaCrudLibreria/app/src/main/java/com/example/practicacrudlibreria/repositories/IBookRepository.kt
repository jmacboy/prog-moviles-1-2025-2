package com.example.practicacrudlibreria.repositories

import com.example.practicacrudlibreria.data.remote.models.BookDto
import com.example.practicacrudlibreria.data.remote.models.NewBookDto
import com.example.practicacrudlibreria.presentation.models.BookUI

interface IBookRepository {

    suspend fun getAllBooks(): Result<List<BookUI>>

    suspend fun getBookById(id: Int): Result<BookUI>

    suspend fun createBook(book: NewBookDto): Result<BookUI>

    suspend fun deleteBook(id: Int): Result<Unit>

    suspend fun updateBook(id: Int, book: NewBookDto): Result<BookUI>

}