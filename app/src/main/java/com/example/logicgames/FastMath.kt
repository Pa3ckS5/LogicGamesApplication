package com.example.logicgames

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun FastMathGame() {
    var number1 by remember { mutableStateOf(0) }
    var number2 by remember { mutableStateOf(0) }
    var correctAnswer by remember { mutableStateOf(0) }
    var operation by remember { mutableStateOf("+") }
    var userAnswer by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var timer by remember { mutableStateOf(12) }
    var timeUp by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }

    fun generateNewProblem() {
        val operations = listOf("+", "-")
        operation = operations.random()
        number1 = Random.nextInt(1, 100)
        number2 = Random.nextInt(1, 100)
        correctAnswer = when (operation) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            else -> 0
        }
        userAnswer = ""
        message = ""
        timer = 12
        timeUp = false
    }

    LaunchedEffect(timer) {
        while (timer > 0 && !timeUp && message.isEmpty()) {
            delay(1000L)
            timer--
        }
        if (timer == 0 && message.isEmpty()) {
            message = "Time's up!"
            timeUp = true
            score = 0 // Reset score on time up
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80003C00)) // dark green, 50% transparency
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "What is $number1 $operation $number2?", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = userAnswer,
                onValueChange = { userAnswer = it },
                label = { Text("Your answer") },
                enabled = !timeUp,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Time left: $timer seconds", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (!timeUp) {
                        if (userAnswer.toIntOrNull() == correctAnswer) {
                            message = "Correct!"
                            score++
                        } else {
                            message = "Wrong!"
                            score = 0
                        }
                        timeUp = true
                    }
                },
                enabled = !timeUp
            ) {
                Text("Submit")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Score: $score", fontSize = 20.sp,
                style = TextStyle(fontSize = 14.sp, fontStyle = FontStyle.Italic)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(message, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            if (timeUp) {
                Button(onClick = { generateNewProblem() }) {
                    Text("Next Problem")
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        generateNewProblem()
    }
}