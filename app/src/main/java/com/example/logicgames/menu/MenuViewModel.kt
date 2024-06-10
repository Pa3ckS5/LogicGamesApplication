package com.example.logicgames.menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicgames.data.AttemptsRepository
import kotlinx.coroutines.launch


data class MenuModel(
    val gameList: List<Game> = listOf()
)
class MenuViewModel(
    savedStateHandle: SavedStateHandle,
    private val attemptsRepository: AttemptsRepository
) : ViewModel() {
    var uiState by mutableStateOf(
        MenuModel(
            gameList = listOf(
                MastermindObject,
                FastMathObject,
                ExampleGameObject,
                ExampleGameObject,
                ExampleGameObject,
                ExampleGameObject
            )
        )
    )
        private set

    init {
        initializeHighestScores()
    }

    private fun initializeHighestScores() {
        uiState.gameList.forEachIndexed { index, game: Game ->
            viewModelScope.launch {
                attemptsRepository.getMaxScoreStream(game.name).collect { maxScore ->
                    maxScore?.let { score ->
                        val updatedGame = game
                        updatedGame.highestScore = score
                        uiState = uiState.copy(gameList = uiState.gameList.toMutableList().apply {
                            this[index] = updatedGame
                        })
                    }
                }
            }
        }
    }
}
