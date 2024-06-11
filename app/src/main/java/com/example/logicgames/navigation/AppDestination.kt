package com.example.logicgames.navigation

import android.content.res.Resources
import androidx.compose.ui.graphics.Color
import com.example.logicgames.R

interface NavigationDestination {
    /**
     * Unique name to define the path for a composable
     */
    val route: String

    /**
     * String resource id to that contains title to be displayed for the screen.
     */
    val nameRes: Int

    val mainColor: Color
    val backgroundColor: Color
}

object MenuObject : NavigationDestination {
    override val nameRes: Int = R.string.menu_title
    override val route: String = "menu"

    override val mainColor: Color = Color(0xFF6100A5) //8% purple
    override val backgroundColor: Color = Color(0x0E5500FF) //8% purple
}

object ScorelistObject : NavigationDestination {
    override val nameRes: Int = R.string.scorelist_title
    override val route: String = "scorelist"

    override val mainColor: Color = Color(0xFF6100A5) //8% purple
    override val backgroundColor: Color = Color(0x0E5500FF) //8% purple
}