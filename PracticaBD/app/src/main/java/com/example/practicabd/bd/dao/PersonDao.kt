package com.example.practicabd.bd.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practicabd.bd.entities.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM Person")
    suspend fun getAllPeople(): List<Person>

    @Query("SELECT * FROM Person WHERE id = :id")
    suspend fun getPersonById(id: Int): Person?

    @Insert
    suspend fun insertPerson(person: Person): Long

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)
}