package com.example.logicgames.app

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.logicgames.game.example_game.ExampleGameViewModel
import com.example.logicgames.game.fast_math.FastMathViewModel
import com.example.logicgames.game.mastermind.MastermindViewModel
import com.example.logicgames.menu.MenuViewModel
import com.example.logicgames.scorelist.ScorelistViewModel

/**
 * Object providing ViewModel instances for the app.
 */
object AppViewModelProvider {
    /**
     * Factory for creating ViewModels.
     */
    val Factory = viewModelFactory {
        initializer {
            MenuViewModel(
                this.createSavedStateHandle(),
                logicGamesApplication().container.itemsRepository
            )
        }
        initializer {
            ScorelistViewModel(
                this.createSavedStateHandle(),
                logicGamesApplication().container.itemsRepository
            )
        }
        initializer {
            MastermindViewModel(
                this.createSavedStateHandle(),
                logicGamesApplication().container.itemsRepository
            )
        }
        initializer {
            FastMathViewModel(
                this.createSavedStateHandle(),
                logicGamesApplication().container.itemsRepository
            )
        }
        initializer {
            ExampleGameViewModel(
                this.createSavedStateHandle(),
                logicGamesApplication().container.itemsRepository
            )
        }
    }
}

/**
 * Extension function to retrieve the LogicGamesApp instance from CreationExtras.
 * @receiver The CreationExtras object.
 * @return The LogicGamesApp instance.
 *
 * Modified from: https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#2
 */
fun CreationExtras.logicGamesApplication(): LogicGamesApp =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as LogicGamesApp)