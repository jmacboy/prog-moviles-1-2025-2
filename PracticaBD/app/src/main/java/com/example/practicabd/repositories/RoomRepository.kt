package com.example.practicabd.repositories

import android.content.Context
import androidx.room.Room
import com.example.practicabd.bd.AppDatabase

object RoomRepository {
    fun getDb(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "prueba-db"
        )
            .allowMainThreadQueries()
            .build()
    }
}