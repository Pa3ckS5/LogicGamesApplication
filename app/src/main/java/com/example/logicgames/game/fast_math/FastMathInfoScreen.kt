package com.example.logicgames.game.fast_math

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.logicgames.app.AppViewModelProvider
import com.example.logicgames.game.mastermind.MastermindInfoViewModel

@Composable
fun FastMathInfoScreen(
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
            //levels
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = { navController.navigate("fastmath") }) {
            Text("Start Game")
        }
    }
}