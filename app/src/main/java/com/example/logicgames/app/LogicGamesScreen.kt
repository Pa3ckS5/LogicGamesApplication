package com.example.logicgames.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.logicgames.navigation.NavigationComponent

@Composable
fun LogicGamesScreen() {
    val navController = rememberNavController()
    NavigationComponent(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogicGamesTopBar(
    title: String,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0x40606060))
            .padding(14.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceAround

        ) {
            Text(
                text = title,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}