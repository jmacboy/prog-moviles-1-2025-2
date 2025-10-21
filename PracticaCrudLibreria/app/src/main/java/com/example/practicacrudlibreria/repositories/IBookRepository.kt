package com.example.practicacrudlibreria.repositories

import com.example.practicacrudlibreria.data.remote.models.BookDto

interface IBookRepository {

    suspend fun getAllBooks(): Result<List<BookDto>>

    suspend fun getBookById(id: Int): Result<BookDto>

}