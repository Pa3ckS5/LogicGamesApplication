package com.example.logicgames.app

import android.app.Application
import com.example.logicgames.data.AppContainer
import com.example.logicgames.data.AppDataContainer

/**
 * Custom application class for Logic Games.
 */
class LogicGamesApp : Application() {
    lateinit var container: AppContainer
    val app = this

    companion object {
        const val CHANNEL_ID = "welcome_channel"
        const val NOTIFICATION_ID = 1
    }

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(app)
        createNotificationChannel()
        sendWelcomeNotification()
    }

    /**
     * Creates a notification channel for displaying notifications.
     */
    private fun createNotificationChannel() {
        // Only creates a notification channel for Android Oreo (API 26) and above.
    }

    /**
     * Sends a welcome notification to the user.
     */
    private fun sendWelcomeNotification() {
        // Sends a welcome notification to the user if permission is granted.
    }
}
