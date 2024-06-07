package com.example.logicgames

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LogicGames() {
    val navController = rememberNavController()
    NavigationComponent(navController = navController)
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "startScreen") {
        composable("startScreen") { StartScreen(navController) }
        composable("menu") { Menu(navController) }
        composable("mastermind") { MastermindGame() }
        composable("fastmath") { FastMathGame() }
    }
}