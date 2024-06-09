package com.example.logicgames.game.mastermind

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logicgames.app.AppViewModelProvider

@Composable
fun MastermindInfoScreen(
    viewModel: MastermindViewModel = viewModel(factory = AppViewModelProvider.Factory),

    ) {
    val uiState = viewModel.uiState


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Game info")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Info...")
        Spacer(modifier = Modifier.height(40.dp))
        Text("Game options")
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Switch(
                checked = uiState.info.repeatColors,
                onCheckedChange = { viewModel.setColorRepetition(it) }
            )
            Text(
                text = "color repetition",
                modifier = Modifier
                    .padding(8.dp))
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = { viewModel.setShowedInfo(false) }) {
            Text("Start Game")
        }
    }
}