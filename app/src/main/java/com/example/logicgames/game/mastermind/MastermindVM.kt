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
import com.example.logicgames.menu.MastermindObject
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.random.Random

/**
 * Data class representing the information model for the Mastermind game.
 * @param isShowed Flag indicating whether the information is currently displayed.
 * @param repeatColors Flag indicating whether colors can be repeated in the code.
 */
data class MastermindInfoModel(
    val isShowed: Boolean = true,
    val repeatColors: Boolean = false
)

/**
 * Data class representing the model for the Mastermind game.
 * @param info Information model for the game.
 * @param colors List of available colors for the game.
 * @param secretCode List representing the secret code to be guessed.
 * @param currentGuess List representing the current guess of the player.
 * @param attempts List of all attempts made by the player.
 * @param feedback List of feedback for each attempt indicating the correctness of colors and positions.
 * @param gameOver Flag indicating whether the game is over.
 * @param gameWon Flag indicating whether the player has won the game.
 * @param score The player's score in the game.
 * @param maxAttempts Maximum number of attempts allowed in the game.
 */
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

/**
 * ViewModel class for the Mastermind game.
 * @property savedStateHandle SavedStateHandle instance to handle saved state.
 * @property attemptsRepository Repository for handling attempts.
 */
class MastermindViewModel(
    savedStateHandle: SavedStateHandle,
    private val attemptsRepository: AttemptsRepository
) : ViewModel() {
    private val colors = listOf(
        Color.Yellow, Color.Red, Color.Green, Color.Gray, Color.Cyan, Color.Magenta
    )

    /**
     * Mutable state for the UI representation of the Mastermind game.
     */
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
        )
    )
        private set

    init {
        resetGame()
    }

    /**
     * Sets if colors can repeat .
     * @param value Flag indicating whether the information is showed.
     */
    fun setColorRepetition(value: Boolean) {
        uiState = uiState.copy(
            info = uiState.info.copy(repeatColors = value)
        )
        resetGame()
    }

    /**
     * Sets whether the information is showed.
     * @param value Flag indicating whether the information is showed.
     */
    fun setShowedInfo(value: Boolean) {
        uiState = uiState.copy(
            info = uiState.info.copy(isShowed = value)
        )
        if (!value) resetGame()
    }

    /**
     * Resets the game.
     */
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

    /**
     * Updates the guess at the given index.
     * @param index Index of the guess to update.
     */
    fun updateGuess(index: Int) {
        val newGuess = uiState.currentGuess.toMutableList()
        newGuess[index] =
            uiState.colors[(uiState.colors.indexOf(uiState.currentGuess[index]) + 1) % uiState.colors.size]
        uiState = uiState.copy(currentGuess = newGuess)
    }

    /**
     * Checks the current guess.
     */
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
        if (uiState.attempts.size >= uiState.maxAttempts || gameWon) {
            gameOver = true
        }

        var score = 0
        if (gameWon) {
            score = uiState.maxAttempts - uiState.attempts.size + 1
            recordAttempt("Mastermind", score)
        } else {
            score = uiState.maxAttempts - uiState.attempts.size
        }

        uiState = uiState.copy(
            gameWon = gameWon,
            gameOver = gameOver,
            score = score
        )
    }

    /**
     * Generates a secret code for the game.
     * @param colors List of colors to choose from.
     * @param repeatColors Flag indicating whether colors can be repeated.
     * @return List of colors representing the secret code.
     */
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

    /**
     * Records an attempt in the repository.
     * @param game Name of the game.
     * @param score Score achieved in the game.
     */
    private fun recordAttempt(game: String, score: Int) {
        viewModelScope.launch {
            attemptsRepository.insertAttempt(
                Attempt(
                    time = Date(),
                    game = game,
                    gameRoute = MastermindObject.route,
                    score = score
                )
            )
        }
    }
}