package com.example.practicabd.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.practicabd.bd.entities.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM Person")
    fun getAllPeople(): List<Person>

    @Insert
    fun insertPerson(person: Person): Long
}