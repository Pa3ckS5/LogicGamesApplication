package com.example.logicgames.scorelist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicgames.data.Attempt
import com.example.logicgames.data.AttemptsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Model class representing the state of the score list screen.
 *
 * @param itemList The list of attempts displayed in the score list.
 */
data class ScorelistModel(val itemList: List<Attempt> = listOf())

/**
 * ViewModel class for managing the data and state of the score list screen.
 *
 * @param savedStateHandle The handle to saved state.
 * @param itemsRepository The repository for accessing attempt data.
 */
class ScorelistViewModel(
    savedStateHandle: SavedStateHandle,
    itemsRepository: AttemptsRepository
) : ViewModel() {

    /**
     * Flow representing the state of the score list screen.
     */
    val uiState: StateFlow<ScorelistModel> =
        itemsRepository.getAllAttemptsStream().map { ScorelistModel(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ScorelistModel()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
