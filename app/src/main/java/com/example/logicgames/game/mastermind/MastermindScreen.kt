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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logicgames.R
import com.example.logicgames.app.AppViewModelProvider
import com.example.logicgames.app.LogicGamesTopBar
import com.example.logicgames.menu.MastermindObject

/**
 * Composable function for displaying the Mastermind game screen.
 * @param viewModel The ViewModel for managing the Mastermind game.
 */
@Composable
fun MastermindScreen(viewModel: MastermindViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val uiState = viewModel.uiState

    if (uiState.info.isShowed) {
        MastermindInfoScreen(viewModel)
    } else {

        Scaffold(
            topBar = { LogicGamesTopBar(title = stringResource(id = MastermindObject.nameRes), MastermindObject.mainColor) },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MastermindObject.backgroundColor),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.color_repetition) +": "+ if(uiState.info.repeatColors) stringResource(
                            R.string.yes
                        )  else stringResource(R.string.no),
                        style = TextStyle(fontSize = 14.sp, fontStyle = FontStyle.Italic),
                    )

                    uiState.attempts.reversed().forEachIndexed { index, attempt ->
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .height(45.dp)
                        ) {
                            Text(
                                text = "${uiState.attempts.size - index}",
                                fontSize = 24.sp,
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .align(Alignment.CenterStart)
                            )
                            Row(modifier = Modifier.align(Alignment.Center)) {
                                attempt.forEach { color ->
                                    Box(
                                        modifier = Modifier
                                            .size(36.dp)
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
                        text = if (uiState.gameWon) stringResource(R.string.won_congratulation)
                            else stringResource( R.string.game_over) + " " +
                                stringResource(R.string.correct_combination_display),
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
                        text = stringResource(R.string.score_display, uiState.score),
                        modifier = Modifier.padding(16.dp),
                        style = TextStyle(fontSize = 14.sp, fontStyle = FontStyle.Italic)
                    )
                    Button(onClick = { viewModel.resetGame() }) {
                        Text(stringResource(R.string.play_again))
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
                        Text(stringResource(R.string.submit_guess))
                    }
                }
            }
        }
    }
}