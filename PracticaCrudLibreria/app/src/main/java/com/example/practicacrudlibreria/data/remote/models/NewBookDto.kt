package com.example.practicacrudlibreria.data.remote.models

import com.google.gson.annotations.SerializedName

data class NewBookDto (
    val autor: String? = "",
    val calificacion: Int? = 0,
    val editorial: String? = "",
    val imagen: String? = "",
    val isbn: String? = "",
    val nombre: String? = "",
    val sinopsis: String? = "",
){
}