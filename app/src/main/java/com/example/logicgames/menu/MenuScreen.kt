package com.example.logicgames.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.logicgames.R
import com.example.logicgames.app.AppViewModelProvider
import com.example.logicgames.app.LogicGamesTopBar
import com.example.logicgames.navigation.MenuObject

/**
 * Composable function representing the menu screen.
 * @param navController The navigation controller.
 * @param viewModel The view model for the menu screen.
 */
@Composable
fun MenuScreen(
    navController: NavController,
    viewModel: MenuViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = { LogicGamesTopBar(title = stringResource(id = MenuObject.nameRes), MenuObject.mainColor) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("scorelist") }
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Scorelist"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MenuObject.backgroundColor)
        ) {
            var size = uiState.gameList.size
            val overallScore = uiState.gameList.sumOf { it.highestScore }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    OverallScoreText(numberOfGames = size, overallScore = overallScore)
                }
                items(uiState.gameList) { game ->
                    GameCard(game = game, onClick = { navController.navigate(game.route) })
                }
            }
        }
    }
}

/**
 * Composable function representing the overall score text.
 * @param numberOfGames Number of games.
 * @param overallScore Overall score.
 */
@Composable
fun OverallScoreText(numberOfGames: Int, overallScore: Int) {
    Text(
        text = stringResource(R.string.overall_score_display, numberOfGames, overallScore),
        modifier = Modifier.padding(16.dp),
        style = TextStyle(fontSize = 14.sp, fontStyle = FontStyle.Italic)
    )
}

/**
 * Composable function representing a game card.
 * @param game The game to display.
 * @param onClick Callback function when the card is clicked.
 */
@Composable
fun GameCard(
    game: Game,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(modifier = Modifier.height(150.dp)) {
            Image(
                painter = painterResource(id = game.imageResId),
                contentDescription = stringResource(id = game.nameRes),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(stringResource(R.string.highest_score_display) + game.highestScore, style = TextStyle(fontSize = 12.sp, fontStyle = FontStyle.Italic), color = Color.White)
                Text(stringResource(id = game.nameRes), style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
        }
    }
}
