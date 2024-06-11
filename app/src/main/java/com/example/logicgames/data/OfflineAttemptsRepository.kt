package com.example.logicgames.data

import kotlinx.coroutines.flow.Flow


/**
 * Repository implementation for managing offline attempts.
 * @param attemptDao The data access object for attempts.
 *
 * Modified from: https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#2
 */
class OfflineAttemptsRepository(private val attemptDao: AttemptDao) : AttemptsRepository {
    /**
     * Retrieves a Flow emitting a list of all attempts.
     * @return A Flow emitting a list of attempts.
     */
    override fun getAllAttemptsStream(): Flow<List<Attempt>> = attemptDao.getAllAttempts()

    /**
     * Retrieves a Flow emitting a single attempt by its ID.
     * @param id The ID of the attempt.
     * @return A Flow emitting the attempt.
     */
    override fun getAttemptStream(id: Int): Flow<Attempt?> = attemptDao.getAttempt(id)

    /**
     * Inserts an attempt into the database.
     * @param attempt The attempt to be inserted.
     */
    override suspend fun insertAttempt(attempt: Attempt) = attemptDao.insert(attempt)

    /**
     * Retrieves a Flow emitting the maximum score achieved for a specific game route.
     * @param gameRoute The route of the game.
     * @return A Flow emitting the maximum score.
     */
    override fun getMaxScoreStream(gameRoute: String): Flow<Int?> = attemptDao.getMaxScore(gameRoute)
}