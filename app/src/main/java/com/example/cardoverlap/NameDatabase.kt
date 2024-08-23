package com.example.cardoverlap

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [Name::class], version = 1, exportSchema = false)
abstract class NameDatabase : RoomDatabase() {

    abstract fun nameDao(): NameDao

    companion object {
        @Volatile
        private var INSTANCE: NameDatabase? = null

        fun getDatabase(context: Context): NameDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NameDatabase::class.java,
                    "name_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
