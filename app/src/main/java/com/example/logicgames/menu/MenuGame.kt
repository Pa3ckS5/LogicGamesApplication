package com.example.logicgames.menu

import android.content.res.Resources
import androidx.compose.ui.graphics.Color
import com.example.logicgames.R
import com.example.logicgames.navigation.NavigationDestination

/**
 * Interface representing a game.
 */
interface Game {
    val nameRes: Int // Resource ID for the name of the game.
    val route: String // Route for navigation to the game screen.

    val infoRes: Int // Resource ID for the information about the game.
    val imageResId: Int // Resource ID for the image representing the game.
    var highestScore: Int // Highest score achieved in the game.
}

/**
 * Object representing the Mastermind game.
 */
object MastermindObject : Game, NavigationDestination {
    override val nameRes: Int = R.string.mastermind_title
    override val route: String = "mastermind"

    override val infoRes: Int = R.string.mastermind_info
    override val imageResId: Int = R.drawable.mastermind_image
    override var highestScore: Int = 0

    override val mainColor: Color = Color(0xFF0000C0) // 100% blue
    override val backgroundColor: Color = Color(0x40004AFF) // 25% blue
}

/**
 * Object representing the Fast Math game.
 */
object FastMathObject : Game, NavigationDestination {
    override val nameRes: Int = R.string.fastmath_title
    override val route: String = "fastmath"

    override val infoRes: Int = R.string.fastmath_info
    override val imageResId: Int = R.drawable.fastmath_image
    override var highestScore: Int = 0

    override val mainColor: Color = Color(0xFF007E0B) // 100% green
    override val backgroundColor: Color = Color(0x80005800) // 50% green
}

/**
 * Object representing an example game.
 */
object ExampleGameObject : Game, NavigationDestination {
    override val nameRes: Int = R.string.examplegame_name
    override val route: String = "examplegame"

    override val infoRes: Int = R.string.examplegame_info
    override val imageResId: Int = R.drawable.examplegame_image
    override var highestScore: Int = 0

    override val mainColor: Color = Color(0xFF606060) // 100% grey
    override val backgroundColor: Color = Color(0x80606060) // 50% grey
}

