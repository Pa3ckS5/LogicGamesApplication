package com.example.logicgames.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface AttemptsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllAttemptsStream(): Flow<List<Attempt>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getAttemptStream(id: Int): Flow<Attempt?>

    /**
     * Insert item in the data source
     */
    suspend fun insertAttempt(attempt: Attempt)

    fun getMaxScoreStream(game: String): Flow<Int?>
}
