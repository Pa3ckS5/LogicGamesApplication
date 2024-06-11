package com.example.logicgames.scorelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logicgames.app.AppViewModelProvider
import com.example.logicgames.data.Attempt
import com.example.logicgames.data.formatedDate
import com.example.logicgames.data.formatedScore
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import com.example.logicgames.R
import com.example.logicgames.app.LogicGamesTopBar
import com.example.logicgames.navigation.ScorelistObject

@Composable
fun ScorelistScreen(
    modifier: Modifier = Modifier,
    viewModel: ScorelistViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        topBar = { LogicGamesTopBar(title = stringResource(id = ScorelistObject.nameRes), ScorelistObject.mainColor) },
    ) { innerPadding ->
        if (homeUiState.itemList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(ScorelistObject.backgroundColor),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(R.string.no_attempts_yet_lets_play),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(ScorelistObject.backgroundColor)
        ) {
                item {
                    OverallAttemptsText(numberOfAttempts = homeUiState.itemList.size)
                }

                var i: Int = 0
                items(items = homeUiState.itemList, key = { it.id }) { item ->
                    val color = if(i % 2 == 0) Color(0x8066009A) else Color(0x80360077) //colors switch
                    AttemptView(attempt = item, modifier = Modifier.padding(8.dp), color)
                    i++
                }
            }
        }
    }
}

@Composable
fun OverallAttemptsText(numberOfAttempts: Int) {
    Text(
        text = stringResource(R.string.overall_attempts_display) + numberOfAttempts,
        modifier = Modifier.padding(16.dp),
        style = TextStyle(fontSize = 14.sp, fontStyle = FontStyle.Italic)
    )
}

@Composable
private fun AttemptView(
    attempt: Attempt, modifier: Modifier = Modifier, color: Color
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = color, shape = RoundedCornerShape(8.dp))


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                AttemptAtributeView(name = "Game", value = attempt.game)
            }
            Spacer(Modifier.width(4.dp))
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                AttemptAtributeView(name = "Date", value = attempt.formatedDate())
                AttemptAtributeView(name = "Score", value = attempt.formatedScore())
            }
        }
    }
}

@Composable
private fun AttemptAtributeView(
    name: String,
    value: String
) {
    Column(
        modifier = Modifier.padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = name,
            style = TextStyle(fontSize = 12.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall
        )
    }
}