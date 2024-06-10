package com.example.logicgames.game.example_game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logicgames.app.AppViewModelProvider
import com.example.logicgames.app.LogicGamesTopBar
import com.example.logicgames.menu.MastermindObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExampleGameScreen(viewModel: ExampleGameViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = { LogicGamesTopBar(title = MastermindObject.name) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0x80606060)), //50% grey
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Write new score", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = uiState.score.toString(),
                onValueChange = { newValue: String ->
                    val newScore = newValue.toIntOrNull()
                    if (newScore != null) {
                        viewModel.setScore(newScore)
                    }
                },
                label = { Text("Score") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.onSubmit() },
            ) {
                Text("Submit")
            }
        }
    }
}