package com.example.logicgames.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.logicgames.navigation.NavigationComponent

@Composable
fun LogicGamesScreen() {
    val navController = rememberNavController()
    NavigationComponent(navController = navController)
}

