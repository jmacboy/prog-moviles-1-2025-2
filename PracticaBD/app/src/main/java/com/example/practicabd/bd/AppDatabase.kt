package com.example.practicabd.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicabd.bd.dao.PersonDao
import com.example.practicabd.bd.entities.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}