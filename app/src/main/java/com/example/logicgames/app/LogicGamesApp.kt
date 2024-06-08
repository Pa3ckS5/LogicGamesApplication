package com.example.logicgames.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.logicgames.app.NavigationComponent

@Composable
fun LogicGames() {
    val navController = rememberNavController()
    NavigationComponent(navController = navController)
}

