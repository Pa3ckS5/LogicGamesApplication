package com.example.logicgames.game.fast_math

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logicgames.app.AppViewModelProvider
import com.example.logicgames.app.LogicGamesTopBar
import com.example.logicgames.game.LevelSelection
import com.example.logicgames.menu.FastMathObject
import com.example.logicgames.menu.MastermindObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FastMathInfoScreen(
    viewModel: FastMathViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = { LogicGamesTopBar(title = stringResource(id = MastermindObject.nameRes)) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(FastMathObject.backgroundColor),
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
                LevelSelection(
                    levels = uiState.info.levels,
                    selectedLevel = uiState.info.selectedLevel,
                    onLevelSelected = { level -> viewModel.setLevel(level) }
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = { viewModel.setShowedInfo(false) }) {
                Text("Start Game")
            }
        }
    }
}