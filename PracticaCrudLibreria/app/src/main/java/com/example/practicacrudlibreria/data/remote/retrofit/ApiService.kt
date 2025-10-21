package com.example.practicacrudlibreria.data.remote.retrofit

import com.example.practicacrudlibreria.data.remote.models.BookDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


    @GET("api/libros")
    suspend fun getBooks(): Response<List<BookDto>>
}