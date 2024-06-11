package com.example.logicgames.data

import android.content.Context

/**
 * App container for Dependency injection.
 *
 * Modified from: https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#2
 */
interface AppContainer {
    val itemsRepository: AttemptsRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineAttemptsRepository]
 *
 * Modified from: https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#2
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [AttemptsRepository]
     *
     * Modified from: https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#2
     */
    override val itemsRepository: AttemptsRepository by lazy {
        OfflineAttemptsRepository(AttemptsDatabase.getDatabase(context).attemptDao())
    }
}