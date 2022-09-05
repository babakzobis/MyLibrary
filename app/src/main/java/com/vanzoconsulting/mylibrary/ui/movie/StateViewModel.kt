package com.vanzoconsulting.mylibrary.ui.movie

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class StateViewModel<U : UiState<*>>: ViewModel() {

    abstract var _uiState: MutableStateFlow<U>
    abstract val uiState: StateFlow<U>

    fun userMessageShown() {
        _uiState.update { currentState ->
            currentState.eraseUserMessage() as U
        }
    }
}