package com.example.logicgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.logicgames.app.LogicGames
import com.example.logicgames.ui.theme.LogicGamesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LogicGamesTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    LogicGames()
                }
            }
        }
    }
}