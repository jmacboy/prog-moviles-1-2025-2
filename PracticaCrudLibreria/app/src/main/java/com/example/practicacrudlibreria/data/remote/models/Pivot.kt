package com.example.practicacrudlibreria.data.remote.models

import com.google.gson.annotations.SerializedName

data class Pivot(
    @SerializedName("genero_id")
    val generoId: Int,
    @SerializedName("libro_id")
    val libroId: Int
)