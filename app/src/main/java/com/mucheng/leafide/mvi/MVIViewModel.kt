package com.mucheng.leafide.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

interface UIState
interface UIIntent

@Suppress("LeakingThis")
abstract class MVIViewModel<S : UIState, I : UIIntent> : ViewModel() {

    protected abstract val initialState: S

    private val mutableStateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)

    val state: StateFlow<S> = mutableStateFlow.asStateFlow()

    val intent: Channel<I> = Channel(Channel.UNLIMITED)

    init {
        viewModelScope.launch {
            intent.consumeAsFlow().collect {
                handleIntent(it)
            }
        }
    }

    protected abstract fun handleIntent(intent: I)

    protected fun updateState(state: S) {
        mutableStateFlow.value = state
    }

}