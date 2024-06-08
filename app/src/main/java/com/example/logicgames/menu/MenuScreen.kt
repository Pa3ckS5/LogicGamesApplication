package com.example.logicgames.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Menu(navController: NavController) {
    val games = listOf(
        Mastermind(),
        FastMath()
    )
    Column {
        var size = games.size
        val overallScore = games.sumOf { it.highestScore }

        Text(
            text = "Overall score in $size games: $overallScore",
            modifier = Modifier.padding(16.dp),
            style = TextStyle(fontSize = 14.sp, fontStyle = FontStyle.Italic)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(games) { game ->
                GameCard(game = game, onClick = { navController.navigate(game.route + "_info") })
            }
        }
    }

}

@Composable
fun GameCard(game: Game, onClick: () -> Unit) {
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
                contentDescription = game.name,
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
                Text("Highest Score: ${game.highestScore}", style = TextStyle(fontSize = 12.sp, fontStyle = FontStyle.Italic), color = Color.White)
                Text(game.name, style = MaterialTheme.typography.titleLarge, color = Color.White)
            }
        }
    }
}