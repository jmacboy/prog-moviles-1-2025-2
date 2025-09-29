package com.example.practicabd.repositories

import android.content.Context
import androidx.room.Room
import com.example.practicabd.bd.AppDatabase
import com.example.practicabd.bd.entities.Person

object PersonRepository {
    fun getAllPeople(context: Context): List<Person> {
        return AppDatabase
            .getInstance(context)
            .personDao()
            .getAllPeople()
    }

    fun insertPerson(context: Context, person: Person): Long {
        return AppDatabase
            .getInstance(context)
            .personDao()
            .insertPerson(person)
    }

    fun deletePerson(context: Context, person: Person) {
        AppDatabase
            .getInstance(context)
            .personDao()
            .deletePerson(person)
    }
}