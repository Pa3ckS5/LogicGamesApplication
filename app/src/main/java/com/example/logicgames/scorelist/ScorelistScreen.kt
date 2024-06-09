package com.example.logicgames.scorelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.logicgames.app.AppViewModelProvider
import com.example.logicgames.data.Attempt
import com.example.logicgames.data.formatedDate
import com.example.logicgames.data.formatedScore


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScorelistScreen(
    modifier: Modifier = Modifier,
    viewModel: ScorelistViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { innerPadding ->
        HomeBody(
            itemList = homeUiState.itemList,
            modifier = modifier.fillMaxSize(),
            contentPadding = innerPadding
        )
    }
}

@Composable
private fun HomeBody(
    itemList: List<Attempt>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (itemList.isEmpty()) {
            Text(
                text = "No attempts", //stringResource("R.string.no_item_description")
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding),
            )
        } else {
            InventoryList(
                itemList = itemList,
                contentPadding = contentPadding,
                modifier = Modifier.padding(horizontal = 8.dp)//dimensionResource(id = R.dimen.padding_small)
            )
        }
    }
}

@Composable
private fun InventoryList(
    itemList: List<Attempt>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = itemList, key = { it.id }) { item ->
            InventoryItem(attempt = item,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Composable
private fun InventoryItem(
    attempt: Attempt, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            ) {
                AttemptAtribute(name = "Game", value = attempt.game)
            }
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            ) {
                AttemptAtribute(name = "Date", value = attempt.formatedDate())
                Spacer(Modifier.weight(1f))
                AttemptAtribute(name = "Score", value = attempt.formatedScore())
            }
        }
    }
}

@Composable
private fun AttemptAtribute(
    name: String,
    value: String
) {
    Column(
        modifier = Modifier.padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium
        )
    }
}