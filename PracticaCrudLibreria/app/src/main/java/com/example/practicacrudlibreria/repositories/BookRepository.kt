package com.example.practicacrudlibreria.repositories

import com.example.practicacrudlibreria.data.remote.models.BookDto
import com.example.practicacrudlibreria.data.remote.models.NewBookDto
import com.example.practicacrudlibreria.data.remote.retrofit.RetrofitInstance
import com.example.practicacrudlibreria.presentation.models.BookUI

class BookRepository : IBookRepository {
    override suspend fun getAllBooks(): Result<List<BookUI>> {
        try {
            val response = RetrofitInstance.api.getBooks()
            if (response.isSuccessful) {
                val books =
                    response.body() ?: return Result.failure(Exception("Empty response body"))
                return Result.success(books.map { bookDto ->
                    BookUI(
                        id = bookDto.id ?: -1,
                        autor = bookDto.autor ?: "",
                        calificacion = bookDto.calificacion ?: 0,
                        editorial = bookDto.editorial ?: "",
                        generos = bookDto.generos?.map { it.nombre } ?: emptyList(),
                        imagen = bookDto.imagen ?: "",
                        nombre = bookDto.nombre ?: "",
                        sinopsis = bookDto.sinopsis ?: "",
                        isbn = bookDto.isbn ?: ""
                    )
                })
            } else {
                return Result.failure(Exception("Error fetching books: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getBookById(id: Int): Result<BookUI> {
        try {
            val response = RetrofitInstance.api.getBookById(id)
            if (response.isSuccessful) {
                val bookDto =
                    response.body() ?: return Result.failure(Exception("Empty response body"))
                return Result.success(
                    BookUI(
                        id = bookDto.id ?: -1,
                        autor = bookDto.autor ?: "",
                        calificacion = bookDto.calificacion ?: 0,
                        editorial = bookDto.editorial ?: "",
                        generos = bookDto.generos?.map { it.nombre } ?: emptyList(),
                        imagen = bookDto.imagen ?: "",
                        nombre = bookDto.nombre ?: "",
                        sinopsis = bookDto.sinopsis ?: "",
                        isbn = bookDto.isbn ?: ""
                    )
                )
            } else {
                return Result.failure(Exception("Error fetching books: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun createBook(book: NewBookDto): Result<BookUI> {
        try {
            val response = RetrofitInstance.api.createBook(book)
            if (response.isSuccessful) {
                val bookDto =
                    response.body() ?: return Result.failure(Exception("Empty response body"))
                return Result.success(
                    BookUI(
                        id = bookDto.id ?: -1,
                        autor = bookDto.autor ?: "",
                        calificacion = bookDto.calificacion ?: 0,
                        editorial = bookDto.editorial ?: "",
                        generos = bookDto.generos?.map { it.nombre } ?: emptyList(),
                        imagen = bookDto.imagen ?: "",
                        nombre = bookDto.nombre ?: "",
                        sinopsis = bookDto.sinopsis ?: "",
                        isbn = bookDto.isbn ?: ""
                    )
                )
            } else {
                return Result.failure(Exception("Error fetching books: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun deleteBook(id: Int): Result<Unit> {
        try {
            val response = RetrofitInstance.api.deleteBook(id)
            if (response.isSuccessful) {
                response.body() ?: return Result.failure(Exception("Empty response body"))
                return Result.success(
                    Unit
                )
            } else {
                return Result.failure(Exception("Error fetching books: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun updateBook(id: Int, book: NewBookDto): Result<BookUI> {
        try {
            val response = RetrofitInstance.api.updateBook(id, book)
            if (response.isSuccessful) {
                val bookDto =
                    response.body() ?: return Result.failure(Exception("Empty response body"))
                return Result.success(
                    BookUI(
                        id = bookDto.id ?: -1,
                        autor = bookDto.autor ?: "",
                        calificacion = bookDto.calificacion ?: 0,
                        editorial = bookDto.editorial ?: "",
                        generos = bookDto.generos?.map { it.nombre } ?: emptyList(),
                        imagen = bookDto.imagen ?: "",
                        nombre = bookDto.nombre ?: "",
                        sinopsis = bookDto.sinopsis ?: "",
                        isbn = bookDto.isbn ?: ""
                    )
                )
            } else {
                return Result.failure(Exception("Error fetching books: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

}