package com.mucheng.leafide.mvi

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class SetupState(
    val selectedIndex: Int = 0,
    val shouldFinishActivity: Boolean = false,
    val shouldStartActivity: Boolean = false,
) : UIState

sealed class SetupIntent : UIIntent {
    data object Next : SetupIntent()

    data object Prev : SetupIntent()
}

class SetupViewModel : MVIViewModel<SetupState, SetupIntent>() {

    override val initialState: SetupState
        get() = SetupState()

    override fun handleIntent(intent: SetupIntent) {
        when (intent) {
            SetupIntent.Next -> {
                next()
            }

            SetupIntent.Prev -> {
                prev()
            }
        }
    }

    private fun next() {
        viewModelScope.launch {
            if (state.value.selectedIndex + 1 <= 2) {
                updateState(state.value.copy(selectedIndex = state.value.selectedIndex + 1))
            } else {
                updateState(state.value.copy(shouldStartActivity = true))
            }
        }
    }

    private fun prev() {
        viewModelScope.launch {
            if (state.value.selectedIndex - 1 >= 0) {
                updateState(state.value.copy(selectedIndex = state.value.selectedIndex - 1))
            } else {
                updateState(state.value.copy(shouldFinishActivity = true))
            }
        }
    }
}
