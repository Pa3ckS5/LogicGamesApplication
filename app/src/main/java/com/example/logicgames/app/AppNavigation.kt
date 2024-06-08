package com.example.logicgames.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.logicgames.menu.Menu
import com.example.logicgames.game.fast_math.FastMathInfoScreen
import com.example.logicgames.game.fast_math.FastMathScreen
import com.example.logicgames.game.mastermind.MastermindScreen
import com.example.logicgames.game.mastermind.MastermindInfoScreen

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "startScreen") {
        composable("startScreen") { StartScreen(navController = navController) }
        composable("menu") { Menu(navController = navController) }

        composable("mastermind") { MastermindScreen() }
        composable("mastermind_info") { MastermindInfoScreen(navController = navController) }

        composable("fastmath") { FastMathScreen() }
        composable("fastmath_info") { FastMathInfoScreen(navController = navController) }
    }
}