package com.example.logicgames

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

data class MastermindModel(
    val colors: List<Color>,
    var secretCode: List<Color>,
    var currentGuess: List<Color>,
    var attempts: MutableList<List<Color>>,
    var feedback: MutableList<List<Pair<Color, Boolean>>>,
    var gameOver: Boolean,
    var gameWon: Boolean,
    var score: Int,
    val maxAttempts: Int = 8
)

class MastermindViewModel : ViewModel() {
    private val _state = MutableStateFlow(
        MastermindModel(
            colors = listOf(Color.Yellow, Color.Red, Color.Green, Color.Gray, Color.Cyan, Color.Magenta),
            secretCode = List(4) { Color.Transparent },
            currentGuess = List(4) { Color.Transparent },
            attempts = mutableListOf(),
            feedback = mutableListOf(),
            gameOver = false,
            gameWon = false,
            score = 0
        )
    )
    val state: StateFlow<MastermindModel> = _state.asStateFlow()

    init {
        resetGame()
    }

    fun resetGame() {
        _state.update { currentState ->
            currentState.copy(
                secretCode = List(4) { currentState.colors.random() },
                currentGuess = List(4) { currentState.colors[0] },
                attempts = mutableListOf(),
                feedback = mutableListOf(),
                gameOver = false,
                gameWon = false,
                score = 0
            )
        }
    }

    fun updateGuess(index: Int) {
        _state.update { currentState ->
            if (!currentState.gameOver) {
                val newGuess = currentState.currentGuess.toMutableList()
                newGuess[index] = currentState.colors[(currentState.colors.indexOf(currentState.currentGuess[index]) + 1) % currentState.colors.size]
                currentState.copy(currentGuess = newGuess)
            } else {
                currentState
            }
        }
    }

    fun checkGuess() {
        _state.update { currentState ->
            if (currentState.gameOver) return@update currentState

            val currentFeedback = mutableListOf<Pair<Color, Boolean>>()
            val unmatchedSecret = currentState.secretCode.toMutableList()
            val unmatchedGuess = currentState.currentGuess.toMutableList()

            // Check for correct color and position
            for (i in 0..3) {
                if (currentState.currentGuess[i] == currentState.secretCode[i]) {
                    currentFeedback.add(Pair(Color.Black, true))
                    unmatchedSecret[i] = Color.Transparent
                    unmatchedGuess[i] = Color.Transparent
                }
            }

            // Check for correct color only
            for (i in 0..3) {
                if (unmatchedGuess[i] != Color.Transparent && unmatchedGuess[i] in unmatchedSecret) {
                    currentFeedback.add(Pair(Color.White, false))
                    unmatchedSecret[unmatchedSecret.indexOf(unmatchedGuess[i])] = Color.Transparent
                }
            }

            val newAttempts = currentState.attempts.toMutableList().apply { add(currentState.currentGuess.toList()) }
            val newFeedback = currentState.feedback.toMutableList().apply { add(currentFeedback) }

            val gameWon = currentFeedback.count { it.first == Color.Black } == 4
            val gameOver = newAttempts.size >= currentState.maxAttempts || gameWon
            val score = if (gameOver && gameWon) currentState.maxAttempts - newAttempts.size + 1 else currentState.score

            currentState.copy(
                attempts = newAttempts,
                feedback = newFeedback,
                gameWon = gameWon,
                gameOver = gameOver,
                score = score
            )
        }
    }
}