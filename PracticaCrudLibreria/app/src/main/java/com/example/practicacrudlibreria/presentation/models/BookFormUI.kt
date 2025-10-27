package com.example.practicacrudlibreria.presentation.models

data class BookFormUI(
    var autor: String = "",
    var calificacion: Int = 0,
    var editorial: String = "",
    var imagen: String = "",
    var isbn: String = "",
    var nombre: String = "",
    var sinopsis: String = "",
) {
}