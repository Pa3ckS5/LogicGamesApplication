package com.example.logicgames.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing attempts in the database.
 *
 * Modified from: https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#2
 */
@Dao
interface AttemptDao {
    /**
     * Inserts an attempt into the database.
     * @param attempt The attempt to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(attempt: Attempt)

    /**
     * Retrieves all attempts from the database, ordered by time in descending order.
     * @return A Flow emitting a list of attempts.
     */
    @Query("SELECT * FROM attempts ORDER BY time DESC")
    fun getAllAttempts(): Flow<List<Attempt>>

    /**
     * Retrieves an attempt from the database by its ID.
     * @param id The ID of the attempt to retrieve.
     * @return A Flow emitting the attempt.
     */
    @Query("SELECT * from attempts WHERE id = :id")
    fun getAttempt(id: Int): Flow<Attempt>

    /**
     * Retrieves the maximum score achieved for a specific game route.
     * @param gameRoute The route of the game.
     * @return A Flow emitting the maximum score.
     */
    @Query("SELECT MAX(score) from attempts WHERE gameRoute = :gameRoute")
    fun getMaxScore(gameRoute: String): Flow<Int>
}