package com.example.logicgames.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.logicgames.app.StartScreen
import com.example.logicgames.game.example_game.ExampleGameScreen
import com.example.logicgames.game.fast_math.FastMathScreen
import com.example.logicgames.game.mastermind.MastermindScreen
import com.example.logicgames.menu.MenuScreen
import com.example.logicgames.scorelist.ScorelistScreen

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "startScreen") {

        composable("startScreen") { StartScreen(navController = navController) }
        composable("menu") { MenuScreen(navController = navController) }
        composable ("scorelist") {ScorelistScreen()}

        composable("mastermind") { MastermindScreen() }
        composable("fastmath") { FastMathScreen() }
        composable("examplegame") { ExampleGameScreen() }
    }
}