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

data class MathProblem(
    val number1: Int,
    val number2: Int,
    val operation: String,
    val correctAnswer: Int
)

data class FastMathModel(
    val problem: MathProblem = MathProblem(0, 0, "+", 0),
    val userAnswer: String = "",
    val message: String = "",
    val timer: Int = 12,
    val timeUp: Boolean = false,
    val score: Int = 0
)

class FastMathViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val operations = listOf("+", "-")

    var uiState by mutableStateOf(FastMathModel())
        private set

    init {
        generateNewProblem()
    }

    private fun generateNewProblem() {
        val operation = operations.random()
        val number1 = Random.nextInt(1, 100)
        val number2 = Random.nextInt(1, 100)
        val correctAnswer = when (operation) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            else -> 0
        }

        uiState = FastMathModel(
            problem = MathProblem(number1, number2, operation, correctAnswer),
            timer = 12
        )

        startTimer()
    }

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

    fun onUserAnswerChange(newAnswer: String) {
        uiState = uiState.copy(userAnswer = newAnswer)
    }

    fun onSubmit() {
        if (!uiState.timeUp) {
            val message = if (uiState.userAnswer.toIntOrNull() == uiState.problem.correctAnswer) {
                "Correct!"
            } else {
                "Wrong!"
            }
            val score = if (message == "Correct!") uiState.score + 1 else 0
            uiState = uiState.copy(message = message, score = score, timeUp = true)
        }
    }

    fun onNextProblem() {
        generateNewProblem()
    }
}