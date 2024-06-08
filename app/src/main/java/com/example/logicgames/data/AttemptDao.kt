package com.example.logicgames.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AttemptDao {
    @Insert
    suspend fun insert(attempt: Attempt)

    @Query("SELECT * FROM attempts ORDER BY time DESC")
    fun getAllAttempts(): Flow<List<Attempt>>

    @Query("SELECT * from attempts WHERE id = :id")
    fun getAttempt(id: Int): Flow<Attempt>
}
