package com.example.practicabd.bd.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Person(
    val name: String,
    @ColumnInfo("last_name") val lastName: String,
    val age: Int,
    val city: String,
    @ColumnInfo("birth_date") val birthDate: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}