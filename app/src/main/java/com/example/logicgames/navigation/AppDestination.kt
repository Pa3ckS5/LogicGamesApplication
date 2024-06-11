package com.example.logicgames.navigation

import androidx.compose.ui.graphics.Color
import com.example.logicgames.R

/**
 * Interface representing a navigation destination.
 */
interface NavigationDestination {
    val nameRes: Int // String resource id that contains the title to be displayed for the screen.
    val route: String // Route for navigation to the game screen.

    val mainColor: Color // The main color for the navigation destination.
    val backgroundColor: Color //The background color for the navigation destination.
}

/**
 * Object representing the menu navigation destination.
 */
object MenuObject : NavigationDestination {
    override val nameRes: Int = R.string.menu_title
    override val route: String = "menu"
    override val mainColor: Color = Color(0xFF6100A5) // 8% purple
    override val backgroundColor: Color = Color(0x0E5500FF) // 8% purple
}

/**
 * Object representing the scorelist navigation destination.
 */
object ScorelistObject : NavigationDestination {
    override val nameRes: Int = R.string.scorelist_title
    override val route: String = "scorelist"
    override val mainColor: Color = Color(0xFF6100A5) // 8% purple
    override val backgroundColor: Color = Color(0x0E5500FF) // 8% purple
}
