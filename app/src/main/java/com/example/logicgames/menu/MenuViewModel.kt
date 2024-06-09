package com.example.logicgames.menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.logicgames.data.AttemptsRepository


data class MenuModel(
    val gameList: List<Game> = listOf()
)
class MenuViewModel(
    savedStateHandle: SavedStateHandle,
    private val attemptsRepository: AttemptsRepository
) : ViewModel() {
    var uiState by mutableStateOf(
        MenuModel(
            gameList = listOf(
                Mastermind(),
                FastMath(),
                ExampleGame()
            )
        )
    )
        private set
}