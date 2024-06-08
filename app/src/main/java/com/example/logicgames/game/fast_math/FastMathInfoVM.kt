package com.example.logicgames.game.fast_math

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

data class FastMathInfoModel(
    val repeatColors: Boolean = false
)
class FastMathInfoViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    var uiState by mutableStateOf(FastMathInfoModel())
        private set

    fun setRepeatColors(value: Boolean) {
        uiState = uiState.copy(
            repeatColors = value
        )
    }
}