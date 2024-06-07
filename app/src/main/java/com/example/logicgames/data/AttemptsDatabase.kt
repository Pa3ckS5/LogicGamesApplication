package com.example.logicgames.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Attempt::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AttemptsDatabase : RoomDatabase() {
    abstract fun attemptDao(): AttemptDao

    companion object {
        @Volatile
        private var INSTANCE: AttemptsDatabase? = null

        fun getDatabase(context: Context): AttemptsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AttemptsDatabase::class.java,
                    "attempts_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
