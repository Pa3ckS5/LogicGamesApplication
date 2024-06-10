package com.example.logicgames.menu

import com.example.logicgames.R
import com.example.logicgames.navigation.NavigationDestination

interface Game {
    val name: String
    val route: String
    val imageResId: Int
    var highestScore: Int
}
object MastermindObject : Game, NavigationDestination {
    override val name: String = "Mastermind"
    override val route: String = "mastermind"
    override val imageResId: Int = R.drawable.mastermind_image
    override var highestScore: Int = 0
}

object FastMathObject : Game, NavigationDestination {
    override val name: String = "Fast Math"
    override val route: String = "fastmath"
    override val imageResId: Int = R.drawable.fastmath_image
    override var highestScore: Int = 0
}

object ExampleGameObject : Game, NavigationDestination {
    override val name: String = "Example Game"
    override val route: String = "examplegame"
    override val imageResId: Int = R.drawable.fastmath_image
    override var highestScore: Int = 0
}