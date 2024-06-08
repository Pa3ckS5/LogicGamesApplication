package com.example.logicgames.game.mastermind

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

data class MastermindInfoModel(
    val repeatColors: Boolean = false
)
class MastermindInfoViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    var uiState by mutableStateOf(MastermindInfoModel())
        private set

    fun setRepeatColors(value: Boolean) {
        uiState = uiState.copy(
            repeatColors = value
        )
    }
}