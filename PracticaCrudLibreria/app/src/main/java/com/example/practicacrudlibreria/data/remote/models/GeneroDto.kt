package com.example.practicacrudlibreria.data.remote.models

import com.google.gson.annotations.SerializedName

data class GeneroDto(
    @SerializedName("created_at")
    val createdAt: String,
    val id: Int,
    val nombre: String,
    val pivot: Pivot,
    @SerializedName("updated_at")
    val updatedAt: String
)