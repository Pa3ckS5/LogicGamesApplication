package com.example.logicgames.app

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.logicgames.game.fast_math.FastMathInfoViewModel
import com.example.logicgames.game.fast_math.FastMathViewModel
import com.example.logicgames.game.mastermind.MastermindInfoViewModel
import com.example.logicgames.game.mastermind.MastermindViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            MastermindViewModel(
                this.createSavedStateHandle()
            )
        }

        initializer {
            MastermindInfoViewModel(
                this.createSavedStateHandle()
            )
        }

        initializer {
            FastMathViewModel(
                this.createSavedStateHandle()
            )
        }

        initializer {
            FastMathInfoViewModel(
                this.createSavedStateHandle()
            )
        }


    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.inventoryApplication(): LogicGamesApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LogicGamesApplication)