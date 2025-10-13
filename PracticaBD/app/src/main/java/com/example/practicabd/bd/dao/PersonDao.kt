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
    fun getAllPeople(): List<Person>

    @Query("SELECT * FROM Person WHERE id = :id")
    fun getPersonById(id: Int): Person?

    @Insert
    fun insertPerson(person: Person): Long

    @Update
    fun updatePerson(person: Person)

    @Delete
    fun deletePerson(person: Person)
}