package com.example.logicgames.game.fast_math

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logicgames.app.AppViewModelProvider
import com.example.logicgames.app.LogicGamesTopBar
import com.example.logicgames.menu.FastMathObject
import com.example.logicgames.menu.MastermindObject
import com.example.logicgames.R
import com.example.logicgames.menu.ExampleGameObject

@Composable
fun FastMathScreen(viewModel: FastMathViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val uiState = viewModel.uiState

    if (uiState.info.isShowed) {
        FastMathInfoScreen(viewModel)
    } else {

        Scaffold(
            topBar = { LogicGamesTopBar(title = stringResource(id = FastMathObject.nameRes), FastMathObject.mainColor) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(FastMathObject.backgroundColor),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(
                        R.string.level_display,
                        uiState.info.levels[uiState.info.selectedLevel - 1].number,
                        uiState.info.levels[uiState.info.selectedLevel - 1].description),
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(fontSize = 14.sp, fontStyle = FontStyle.Italic),
                )
                Text(
                    text = stringResource(R.string.solve_problem_display),
                    modifier = Modifier.padding(4.dp),
                    fontSize = 16.sp
                )
                Text(
                    text = "${uiState.problem.number1} ${uiState.problem.operation} ${uiState.problem.number2} =",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = uiState.userAnswer,
                    onValueChange = { viewModel.onUserAnswerChange(it) },
                    label = { Text("Your answer") },
                    enabled = !uiState.timeUp,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.time_left_display, uiState.timer),
                    color = if (uiState.timer <= 3) Color.Red else Color.Unspecified,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.onSubmit() },
                    enabled = !uiState.timeUp
                ) {
                    Text(stringResource(R.string.submit))
                }

                Text(
                    uiState.message,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(R.string.score_display, uiState.score),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(16.dp))
                if (uiState.timeUp) {
                    Button(onClick = { viewModel.onNextProblem() }) {
                        Text(stringResource(R.string.next_problem))
                    }
                }
            }
        }
    }
}