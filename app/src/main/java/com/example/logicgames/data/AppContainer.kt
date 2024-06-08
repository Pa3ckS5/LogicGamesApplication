package com.example.logicgames.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val itemsRepository: AttemptsRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val itemsRepository: AttemptsRepository by lazy {
        OfflineAttemptsRepository(AttemptsDatabase.getDatabase(context).attemptDao())
    }
}