package com.example.logicgames.game.example_game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicgames.data.Attempt
import com.example.logicgames.data.AttemptsRepository
import com.example.logicgames.menu.ExampleGameObject
import kotlinx.coroutines.launch
import java.util.Date

/**
 * Model class representing the state of the example game.
 * @param isInfoShowed Indicates whether the game info is showed.
 * @param score The current score of the game.
 */
data class ExampleGameModel(
    val isInfoShowed: Boolean = true,
    val score: Int = 0
)

/**
 * ViewModel for managing the example game.
 * @param savedStateHandle The SavedStateHandle for accessing saved state data.
 * @param attemptsRepository The repository for managing attempts.
 */
class ExampleGameViewModel(
    savedStateHandle: SavedStateHandle,
    private val attemptsRepository: AttemptsRepository
) : ViewModel() {
    var uiState by mutableStateOf(ExampleGameModel())
        private set

    /**
     * Sets whether the game info is showed.
     * @param value The value to set.
     */
    fun setShowedInfo(value: Boolean) {
        uiState = uiState.copy(
            isInfoShowed = value
        )
    }

    /**
     * Sets the current score of the game.
     * @param value The value to set.
     */
    fun setScore(value: Int) {
        uiState = uiState.copy(
            score = value
        )
    }

    /**
     * Handles the submission of the game.
     */
    fun onSubmit() {
        recordAttempt("Example Game", uiState.score)
        setScore(0)
    }

    /**
     * Records an attempt for the example game.
     * @param game The name of the game.
     * @param score The score achieved in the attempt.
     */
    private fun recordAttempt(game: String, score: Int) {
        viewModelScope.launch {
            attemptsRepository.insertAttempt(
                Attempt(
                    time = Date(),
                    game = game,
                    gameRoute = ExampleGameObject.route,
                    score = score
                )
            )
        }
    }
}