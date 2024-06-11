package com.example.logicgames.menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicgames.data.AttemptsRepository
import kotlinx.coroutines.launch


/**
 * Data class representing the model for the menu screen.
 * @param gameList The list of games to display.
 */
data class MenuModel(
    val gameList: List<Game> = listOf()
)

/**
 * ViewModel class for the menu screen.
 * @param savedStateHandle The saved state handle.
 * @param attemptsRepository The repository for attempts.
 */
class MenuViewModel(
    savedStateHandle: SavedStateHandle,
    private val attemptsRepository: AttemptsRepository
) : ViewModel() {
    /**
     * Mutable state representing the UI state of the menu screen.
     */
    var uiState by mutableStateOf(
        MenuModel(
            gameList = listOf(
                MastermindObject,
                FastMathObject,
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

    /**
     * Initializes the highest scores for each game.
     */
    private fun initializeHighestScores() {
        uiState.gameList.forEachIndexed { index, game: Game ->
            viewModelScope.launch {
                attemptsRepository.getMaxScoreStream(game.route).collect { maxScore ->
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

