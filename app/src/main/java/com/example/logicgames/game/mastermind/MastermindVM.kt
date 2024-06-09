package com.example.logicgames.game.mastermind

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicgames.data.Attempt
import com.example.logicgames.data.AttemptsRepository
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.random.Random

data class MastermindInfoModel(
    val isShowed: Boolean = true,
    val repeatColors: Boolean = false
)

data class MastermindModel(
    var info: MastermindInfoModel = MastermindInfoModel(),

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

class MastermindViewModel(
    savedStateHandle: SavedStateHandle,
    private val attemptsRepository: AttemptsRepository
) : ViewModel() {
    private val colors = listOf(Color.Yellow, Color.Red, Color.Green, Color.Gray, Color.Cyan, Color.Magenta)

    var uiState by mutableStateOf(
        MastermindModel(
            colors = colors,
            secretCode = List(4) { Color.Transparent },
            currentGuess = List(4) { Color.Transparent },
            attempts = mutableListOf(),
            feedback = mutableListOf(),
            gameOver = false,
            gameWon = false,
            score = 0
        ))
        private set

    init {
        resetGame()
    }

    fun setColorRepetition(value: Boolean) {
        uiState = uiState.copy(
            info = uiState.info.copy(repeatColors = value),
        )
        resetGame()
    }

    fun setShowedInfo(value: Boolean) {
        uiState = uiState.copy(
            info = uiState.info.copy(isShowed = value)
        )
    }

    fun resetGame() {
        uiState = uiState.copy(
            secretCode = generateSecretCode(uiState.colors, uiState.info.repeatColors),
            currentGuess = List(4) { uiState.colors[0] },
            attempts = mutableListOf(),
            feedback = mutableListOf(),
            gameOver = false,
            gameWon = false,
            score = 0
        )
    }

    fun updateGuess(index: Int) {
        val newGuess = uiState.currentGuess.toMutableList()
        newGuess[index] = uiState.colors[(uiState.colors.indexOf(uiState.currentGuess[index]) + 1) % uiState.colors.size]
        uiState = uiState.copy(currentGuess = newGuess)
    }

    fun checkGuess() {
        if (uiState.attempts.size >= uiState.maxAttempts) {
            uiState = uiState.copy(gameOver = true)
            return
        }

        val currentFeedback = mutableListOf<Pair<Color, Boolean>>()
        val unmatchedSecret = uiState.secretCode.toMutableList()
        val unmatchedGuess = uiState.currentGuess.toMutableList()

        // Check for correct color and position
        for (i in 0..3) {
            if (uiState.currentGuess[i] == uiState.secretCode[i]) {
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

        uiState.attempts.add(uiState.currentGuess.toList())
        uiState.feedback.add(currentFeedback)

        var gameWon = false
        if (currentFeedback.count { it.first == Color.Black } == 4) {
            gameWon = true
        }

        var gameOver = false
        if (uiState.attempts.size >= uiState.maxAttempts && !gameWon) {
            gameOver = true
        }

        var score = 0
        if (gameWon) {
            score = uiState.maxAttempts - uiState.attempts.size + 1
            recordAttempt("Mastermind", score)
        }


        uiState = uiState.copy(
            gameWon = gameWon,
            gameOver = gameOver,
            score = score
        )

    }

    private fun generateSecretCode(colors: List<Color>, repeatColors: Boolean): List<Color> {
        val availableColors = colors.toMutableList()
        val secretCode = mutableListOf<Color>()
        for (i in 0 until 4) {
            val colorIndex = Random.nextInt(availableColors.size)
            secretCode.add(availableColors[colorIndex])
            if (!repeatColors) {
                availableColors.removeAt(colorIndex)
            }
        }
        return secretCode
    }

    private fun recordAttempt(game: String, score: Int) {
        viewModelScope.launch {
            attemptsRepository.insertAttempt(
                Attempt(
                    time = Date(),
                    game = game,
                    score = score
                )
            )
        }
    }
}