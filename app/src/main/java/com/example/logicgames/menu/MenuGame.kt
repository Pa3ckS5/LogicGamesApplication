package com.example.logicgames.menu

import android.content.res.Resources
import androidx.compose.ui.graphics.Color
import com.example.logicgames.R
import com.example.logicgames.navigation.NavigationDestination

interface Game {
    val nameRes: Int
    val route: String
    val imageResId: Int
    var highestScore: Int
}
object MastermindObject : Game, NavigationDestination {
    override val nameRes: Int = R.string.mastermind_title
    override val route: String = "mastermind"
    override val imageResId: Int = R.drawable.mastermind_image
    override var highestScore: Int = 0
    override val backgroundColor: Color = Color(0x800000A5) //50% blue
}

object FastMathObject : Game, NavigationDestination {
    override val nameRes: Int = R.string.fastmath_title
    override val route: String = "fastmath"
    override val imageResId: Int = R.drawable.fastmath_image
    override var highestScore: Int = 0
    override val backgroundColor: Color = Color(0x80005800) //50% green
}

object ExampleGameObject : Game, NavigationDestination {
    override val nameRes: Int = R.string.examplegame_name
    override val route: String = "examplegame"
    override val imageResId: Int = R.drawable.examplegame_image
    override var highestScore: Int = 0
    override val backgroundColor: Color = Color(0x80606060) //50% grey
}