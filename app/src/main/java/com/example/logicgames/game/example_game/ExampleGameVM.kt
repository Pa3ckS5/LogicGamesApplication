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

data class ExampleGameModel(
    val score: Int = 0
)
class ExampleGameViewModel(
    savedStateHandle: SavedStateHandle,
    private val attemptsRepository: AttemptsRepository
) : ViewModel() {
    var uiState by mutableStateOf(ExampleGameModel())
        private set

    fun setScore(value: Int) {
        uiState = uiState.copy(
            score = value
        )
    }

    fun onSubmit() {
        recordAttempt("Example Game", uiState.score)
    }

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