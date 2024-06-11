package com.example.logicgames.game.fast_math

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import androidx.lifecycle.SavedStateHandle
import com.example.logicgames.data.Attempt
import com.example.logicgames.data.AttemptsRepository
import com.example.logicgames.game.Level
import com.example.logicgames.menu.FastMathObject
import java.util.Date

/**
 * Model class representing the state of the Fast Math game information.
 * @param isShowed Indicates whether the game information is showed.
 * @param levels The list of levels available for the game.
 * @param selectedLevel The currently selected level.
 */
data class FastMathInfoModel(
    val isShowed: Boolean = true,
    val levels: List<Level>,
    val selectedLevel: Int = 1
)

/**
 * Model class representing a mathematical problem in the Fast Math game.
 * @param number1 The first number in the problem.
 * @param number2 The second number in the problem.
 * @param operation The mathematical operation.
 * @param correctAnswer The correct answer to the problem.
 */
data class MathProblem(
    val number1: Int,
    val number2: Int,
    val operation: String,
    val correctAnswer: Int
)

/**
 * Model class representing the state of the Fast Math game.
 * @param info The information about the game.
 * @param problem The current mathematical problem.
 * @param userAnswer The user's answer to the problem.
 * @param message The message to be displayed to the user.
 * @param timer The countdown timer for the game.
 * @param timeUp Indicates whether the time is up.
 * @param score The current score of the game.
 */
data class FastMathModel(
    val info: FastMathInfoModel = FastMathInfoModel(levels = listOf(
        Level(1, "easy"),
        Level(2, "medium"),
        Level(3, "hard")
    )),
    val problem: MathProblem = MathProblem(0, 0, "+", 0),
    val userAnswer: String = "",
    val message: String = "",
    val timer: Int = 12,
    val timeUp: Boolean = false,
    val score: Int = 0
)

/**
 * ViewModel for managing the Fast Math game.
 * @param savedStateHandle The SavedStateHandle for accessing saved state data.
 * @param attemptsRepository The repository for managing attempts.
 */
class FastMathViewModel(
    savedStateHandle: SavedStateHandle,
    private val attemptsRepository: AttemptsRepository
) : ViewModel() {
    private val operations = listOf("+", "-")

    var uiState by mutableStateOf(FastMathModel())
        private set

    init {
        //generateNewProblem(true)
    }

    /**
     * Sets the level of the game.
     * @param value The level to set.
     */
    fun setLevel(value: Int) {
        uiState = uiState.copy(
            info = uiState.info.copy(selectedLevel = value),
        )
    }

    /**
     * Sets whether the game information is showed.
     * @param value The value to set.
     */
    fun setShowedInfo(value: Boolean) {
        uiState = uiState.copy(
            info = uiState.info.copy(isShowed = value)
        )
        if(!value) generateNewProblem(true)
    }

    /**
     * Generates a new mathematical problem.
     * @param scoreReset Indicates whether the score should be reset.
     */
    private fun generateNewProblem(scoreReset: Boolean) {
        val info = uiState.info.copy()
        val score = uiState.score

        val operation = operations.random()
        val number1 = Random.nextInt(1,  100 * uiState.info.selectedLevel)
        val number2 = Random.nextInt(1, 100 * uiState.info.selectedLevel)
        val correctAnswer = when (operation) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            else -> 0
        }

        uiState = FastMathModel(
            info = info,
            problem = MathProblem(number1, number2, operation, correctAnswer),
            timer = 12,
            score = score
        )

        startTimer()
    }

    /**
     * Starts the countdown timer for the game.
     */
    private fun startTimer() {
        viewModelScope.launch {
            while (uiState.timer > 0 && !uiState.timeUp && uiState.message.isEmpty()) {
                delay(1000L)
                uiState = uiState.copy(timer = uiState.timer - 1)
            }
            if (uiState.timer == 0 && uiState.message.isEmpty()) {
                uiState = uiState.copy(message = "Time's up!", timeUp = true, score = 0)
            }
        }
    }

    /**
     * Handles the change in user's answer to the problem.
     * @param newAnswer The new user's answer.
     */
    fun onUserAnswerChange(newAnswer: String) {
        uiState = uiState.copy(userAnswer = newAnswer)
    }

    /**
     * Handles the submission of the user's answer.
     */
    fun onSubmit() {
        val score: Int
        val message: String
        if (!uiState.timeUp) {
            if (uiState.userAnswer.toIntOrNull() == uiState.problem.correctAnswer) {
                message = "Correct!"
                score = uiState.score + 1
            } else {
                message = "Wrong!"
                if(uiState.score > 0) recordAttempt("Fast Math", uiState.score)
                score = 0
            }
            uiState = uiState.copy(message = message, score = score, timeUp = true)
        }
    }

    /**
     * Handles moving to the next problem.
     */
    fun onNextProblem() {
        generateNewProblem(false)
    }

    private fun recordAttempt(game: String, score: Int) {
        viewModelScope.launch {
            attemptsRepository.insertAttempt(
                Attempt(
                    time = Date(),
                    game = game,
                    gameRoute = FastMathObject.route,
                    score = score
                )
            )
        }
    }
}