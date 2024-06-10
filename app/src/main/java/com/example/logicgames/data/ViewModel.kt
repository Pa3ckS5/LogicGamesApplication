package com.example.logicgames.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.*
class GameViewModel(private val attemptDao: AttemptDao) : ViewModel() {

    fun recordAttempt(game: String, gameRoute:String, score: Int) {
        viewModelScope.launch {
            val attempt = Attempt(
                time = Date(),
                game = game,
                gameRoute = gameRoute,
                score = score
            )
            attemptDao.insert(attempt)
        }
    }

    val allAttempts = attemptDao.getAllAttempts()
}