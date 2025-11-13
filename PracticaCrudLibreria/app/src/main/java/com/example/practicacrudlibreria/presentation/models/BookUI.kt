package com.example.practicacrudlibreria.presentation.models

import com.example.practicacrudlibreria.data.remote.models.GeneroDto
import com.google.gson.annotations.SerializedName

data class BookUI
    (
    val autor: String = "",
    val calificacion: Int = 0,
    val editorial: String = "",
    val generos: List<String> = emptyList(),
    val id: Int = 0,
    val imagen: String = "",
    val nombre: String = "",
    val sinopsis: String = "",
    val isbn: String = ""
) {
}