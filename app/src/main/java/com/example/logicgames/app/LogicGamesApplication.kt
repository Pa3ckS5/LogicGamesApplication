package com.example.logicgames.app

import android.app.Application
import com.example.logicgames.data.AppContainer
import com.example.logicgames.data.AppDataContainer

class LogicGamesApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}