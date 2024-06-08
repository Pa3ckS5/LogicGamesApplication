package com.example.logicgames.game.fast_math

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logicgames.app.AppViewModelProvider

@Composable
fun FastMathScreen(viewModel: FastMathViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val uiState = viewModel.uiState

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80003C00)) // dark green, 50% transparency
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "What is ${uiState.problem.number1} ${uiState.problem.operation} ${uiState.problem.number2}?", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = uiState.userAnswer,
                onValueChange = { viewModel.onUserAnswerChange(it) },
                label = { Text("Your answer") },
                enabled = !uiState.timeUp,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Time left: ${uiState.timer} seconds", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.onSubmit() },
                enabled = !uiState.timeUp
            ) {
                Text("Submit")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Score: ${uiState.score}", fontSize = 20.sp,
                style = TextStyle(fontSize = 14.sp, fontStyle = FontStyle.Italic)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(uiState.message, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            if (uiState.timeUp) {
                Button(onClick = { viewModel.onNextProblem() }) {
                    Text("Next Problem")
                }
            }
        }
    }
}