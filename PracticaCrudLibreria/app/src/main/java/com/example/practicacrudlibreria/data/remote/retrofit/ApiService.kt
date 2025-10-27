package com.example.practicacrudlibreria.data.remote.retrofit

import com.example.practicacrudlibreria.data.remote.models.BookDto
import com.example.practicacrudlibreria.data.remote.models.NewBookDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {


    @GET("api/libros")
    suspend fun getBooks(): Response<List<BookDto>>


    @GET("api/libros/{id}")
    suspend fun getBookById(@Path("id") id: Int): Response<BookDto>

    @POST("api/libros")
    suspend fun createBook(@Body book: NewBookDto): Response<BookDto>

    @DELETE("api/libros/{id}")
    suspend fun deleteBook(@Path("id") id: Int): Response<Unit>

    @PUT("api/libros/{id}")
    suspend fun updateBook(@Path("id") id: Int, @Body book: NewBookDto): Response<BookDto>
}