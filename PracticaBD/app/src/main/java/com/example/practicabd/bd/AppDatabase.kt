package com.example.practicabd.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practicabd.bd.dao.PersonDao
import com.example.practicabd.bd.entities.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        const val DB_NAME = "prueba-db"
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, DB_NAME
                )
                    .build()
            }
            return INSTANCE!!
        }
    }
}