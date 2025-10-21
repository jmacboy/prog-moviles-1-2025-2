package com.example.practicacrudlibreria.repositories

import com.example.practicacrudlibreria.data.remote.models.BookDto
import com.example.practicacrudlibreria.data.remote.retrofit.RetrofitInstance

class BookRepository: IBookRepository {
    override suspend fun getAllBooks(): Result<List<BookDto>> {
        try {
            val response = RetrofitInstance.api.getBooks()
            if (response.isSuccessful) {
                val books = response.body() ?: return Result.failure(Exception("Empty response body"))
                return Result.success(books)
            } else {
                return Result.failure(Exception("Error fetching books: ${response.code()} ${response.message()}"))
            }
        }catch (e: Exception){
            return Result.failure(e)
        }
    }

    override suspend fun getBookById(id: Int): Result<BookDto> {
        return Result.failure(Exception("Not implemented yet"))
    }

}