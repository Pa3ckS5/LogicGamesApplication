package com.example.logicgames.game.mastermind

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.logicgames.app.AppViewModelProvider

@Composable
fun MastermindInfoScreen(
    viewModel: MastermindInfoViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController
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
                checked = uiState.repeatColors,
                onCheckedChange = { viewModel.setRepeatColors(it) }
            )
            Text(
                text = "color repetition",
                modifier = Modifier
                    .padding(8.dp))
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = { navController.navigate("mastermind") }) {
            Text("Start Game")
        }
    }
}