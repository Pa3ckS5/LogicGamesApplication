package com.example.logicgames.navigation

import com.example.logicgames.R

interface NavigationDestination {
    /**
     * Unique name to define the path for a composable
     */
    val route: String

    /**
     * String resource id to that contains title to be displayed for the screen.
     */
    val name: String
}

object MenuObject : NavigationDestination {
    override val name: String = "Game Menu"
    override val route: String = "menu"
}

object ScorelistObject : NavigationDestination {
    override val name: String = "Your Scorelist"
    override val route: String = "scorelist"
}