package cz.gemsi.spacex.app.presentation

import cz.gemsi.spacex.core.presentation.AbstractViewModel
import cz.gemsi.spacex.library.navigation.domain.ObserveNavigationEventsUseCase
import cz.gemsi.spacex.library.navigation.model.NavigationEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val observeNavigationEvents: ObserveNavigationEventsUseCase,
) : AbstractViewModel<MainViewModel.State>(State()) {

    private val navigationEvent = MutableStateFlow<NavigationEvent?>(null)

    init {
        state = state.copy(navigationEvent = navigationEvent)

        launch {
            observeNavigationEvents().collect {
                navigationEvent.value = it
            }
        }
    }

    fun onConsumeNavigationEvent() {
        navigationEvent.value = null
    }

    data class State(
        val navigationEvent: StateFlow<NavigationEvent?>? = null,
    ) : AbstractViewModel.State
}