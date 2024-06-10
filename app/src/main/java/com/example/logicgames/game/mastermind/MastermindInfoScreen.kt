package com.example.logicgames.game.mastermind

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logicgames.app.AppViewModelProvider
import com.example.logicgames.app.LogicGamesTopBar
import com.example.logicgames.menu.MastermindObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MastermindInfoScreen(
    viewModel: MastermindViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = { LogicGamesTopBar(title = MastermindObject.name) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0x800000A5)), //50% blue
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
                        .padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = { viewModel.setShowedInfo(false) }) {
                Text("Start Game")
            }
        }
    }
}