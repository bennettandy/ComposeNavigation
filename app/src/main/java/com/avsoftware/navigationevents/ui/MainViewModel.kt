package com.avsoftware.navigationevents.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val state: MutableStateFlow<NavigationViewState> = MutableStateFlow(NavigationViewState(
        isLoading = false
    ))
    val stateFlow: StateFlow<NavigationViewState> = state.asStateFlow()


    val state2: MutableState<NavigationViewState> = mutableStateOf(NavigationViewState(isLoading = false))

    // Navigation Channel
    private val navigationChannel = Channel<NavigationEvent>()
    val navigationalEventsFlow = navigationChannel.receiveAsFlow()


    fun loginClicked(){
        viewModelScope.launch {
                state.value = state.value.copy(isLoading = true)

            delay(3000L)

            state.value = state.value.copy(isLoading = false)

            navigationChannel.send(NavigationEvent.LoggedIn)
        }
    }

}

sealed interface NavigationEvent {
    data object LoggedIn: NavigationEvent
}

data class NavigationViewState(
    val isLoading: Boolean
)