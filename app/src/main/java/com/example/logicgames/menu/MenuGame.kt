package com.example.logicgames.menu

import com.example.logicgames.R

interface Game {
    val name: String
    val route: String
    val imageResId: Int
    var highestScore: Int
}

data class Mastermind(
    override val name: String = "Mastermind",
    override val route: String = "mastermind",
    override val imageResId: Int = R.drawable.mastermind_image,
    override var highestScore: Int = 0
) : Game

data class FastMath(
    override val name: String = "FastMath",
    override val route: String = "fastmath",
    override val imageResId: Int = R.drawable.fastmath_image,
    override var highestScore: Int = 0
) : Game