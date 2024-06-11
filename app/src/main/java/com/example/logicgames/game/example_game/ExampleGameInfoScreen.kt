package com.example.logicgames.game.example_game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logicgames.R
import com.example.logicgames.app.AppViewModelProvider
import com.example.logicgames.app.LogicGamesTopBar
import com.example.logicgames.menu.ExampleGameObject

/**
 * Composable function for displaying the information screen of the example game.
 * @param viewModel The ViewModel for managing the example game.
 */
@Composable
fun ExampleGameInfoScreen(
    viewModel: ExampleGameViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = { LogicGamesTopBar(title = stringResource(id = ExampleGameObject.nameRes), ExampleGameObject.mainColor) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(ExampleGameObject.backgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.game_info),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(12.dp)
            )
            Text(
                text = stringResource(ExampleGameObject.infoRes),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(10.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = { viewModel.setShowedInfo(false) }) {
                Text(stringResource(R.string.start_game))
            }
        }
    }
}