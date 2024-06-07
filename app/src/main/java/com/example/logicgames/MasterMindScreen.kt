package com.example.logicgames

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun MastermindGame(viewModel: MastermindViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80262262)), // Dark green with 50% transparency
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(10.dp)
        ) {
            state.attempts.reversed().forEachIndexed { index, attempt ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "${state.attempts.size - index}", fontSize = 24.sp, modifier = Modifier.padding(end = 16.dp).align(Alignment.CenterStart))
                    Row(modifier = Modifier.align(Alignment.Center)) {
                        attempt.forEach { color ->
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .background(color, CircleShape)
                                    .padding(4.dp)
                            )
                        }
                    }
                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                        state.feedback[state.attempts.size - 1 - index].forEach { (color, _) ->
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .background(color, CircleShape)
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }
        }

        if (state.gameOver) {
            Text(
                text = if (state.gameWon) "Congratulations! You won!" else "Game Over! The correct combination was:",
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
            if (!state.gameWon) {
                Row {
                    state.secretCode.forEach { color ->
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(color, CircleShape)
                                .padding(4.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            Text(
                text = "Score: ${state.score}",
                modifier = Modifier.padding(16.dp),
                style = TextStyle(fontSize = 14.sp, fontStyle = FontStyle.Italic)
            )
            Button(onClick = { viewModel.resetGame() }) {
                Text("Play Again")
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                state.currentGuess.forEachIndexed { index, color ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(color, CircleShape)
                            .clickable { viewModel.updateGuess(index) }
                            .padding(4.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.padding(4.dp),
                onClick = {
                    viewModel.checkGuess()
                }) {
                Text("Submit Guess")
            }
        }
    }
}