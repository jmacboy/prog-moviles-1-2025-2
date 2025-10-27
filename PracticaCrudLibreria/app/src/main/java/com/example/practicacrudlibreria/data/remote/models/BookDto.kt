package com.example.practicacrudlibreria.data.remote.models

import com.google.gson.annotations.SerializedName

data class BookDto(
    val autor: String?,
    val calificacion: Int?,
    @SerializedName("created_at")
    val createdAt: String?,
    val editorial: String?,
    val generos: List<GeneroDto>?,
    val id: Int?,
    val imagen: String?,
    val isbn: String?,
    val nombre: String?,
    val sinopsis: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)