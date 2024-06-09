package com.example.logicgames.game.mastermind

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logicgames.app.AppViewModelProvider


@Composable
fun MastermindScreen(viewModel: MastermindViewModel = viewModel(factory = AppViewModelProvider.Factory)) { //repeatColorsP: Boolean,
    val uiState = viewModel.uiState

    if (uiState.info.isShowed) {
        MastermindInfoScreen(viewModel)
    } else {
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
                Text(text = ": ${uiState.info.repeatColors}") //color rep
                uiState.attempts.reversed().forEachIndexed { index, attempt ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "${uiState.attempts.size - index}", fontSize = 24.sp, modifier = Modifier
                            .padding(end = 16.dp)
                            .align(Alignment.CenterStart))
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
                            uiState.feedback[uiState.attempts.size - 1 - index].forEach { (color, _) ->
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

            if (uiState.gameOver) {
                Text(
                    text = if (uiState.gameWon) "Congratulations! You won!" else "Game Over! The correct combination was:",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp)
                )
                if (!uiState.gameWon) {
                    Row {
                        uiState.secretCode.forEach { color ->
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
                    text = "Score: ${uiState.score}",
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
                    uiState.currentGuess.forEachIndexed { index, color ->
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
}