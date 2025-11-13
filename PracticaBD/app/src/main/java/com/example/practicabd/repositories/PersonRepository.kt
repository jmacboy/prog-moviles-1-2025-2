package com.example.practicabd.repositories

import android.content.Context
import com.example.practicabd.bd.AppDatabase
import com.example.practicabd.bd.entities.Person

class PersonRepository(
    private val context: Context
) {
    suspend fun getAllPeople(): List<Person> {
        return AppDatabase
            .getInstance(context)
            .personDao()
            .getAllPeople()
    }

    suspend fun getPersonById(id: Int): Person? {
        return AppDatabase
            .getInstance(context)
            .personDao()
            .getPersonById(id)
    }

    suspend fun insertPerson(person: Person): Long {
        return AppDatabase
            .getInstance(context)
            .personDao()
            .insertPerson(person)
    }

    suspend fun deletePerson(person: Person) {
        AppDatabase
            .getInstance(context)
            .personDao()
            .deletePerson(person)
    }

    suspend fun updatePerson(person: Person) {
        AppDatabase
            .getInstance(context)
            .personDao()
            .updatePerson(person)
    }
}