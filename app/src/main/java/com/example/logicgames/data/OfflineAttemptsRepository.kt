package com.example.logicgames.data

import kotlinx.coroutines.flow.Flow

class OfflineAttemptsRepository(private val attemptDao: AttemptDao) : AttemptsRepository {
    override fun getAllAttemptsStream(): Flow<List<Attempt>> = attemptDao.getAllAttempts()

    override fun getAttemptStream(id: Int): Flow<Attempt?> = attemptDao.getAttempt(id)

    override suspend fun insertAttempt(attempt: Attempt) = attemptDao.insert(attempt)

    override fun getMaxScoreStream(gameRoute: String): Flow<Int?> = attemptDao.getMaxScore(gameRoute)

}