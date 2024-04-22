package cz.gemsi.spacex.feature.launch.presentation

import cz.gemsi.spacex.core.presentation.AbstractViewModel
import cz.gemsi.spacex.feature.launch.domain.ObserveRocketLaunchEventsUseCase
import cz.gemsi.spacex.feature.launch.domain.RegisterPickUpListenerUseCase
import cz.gemsi.spacex.feature.launch.domain.UnregisterRocketLaunchListenerUseCase
import cz.gemsi.spacex.library.navigation.domain.GoBackUseCase

class RocketLaunchViewModel(
    private val goBack: GoBackUseCase,
    private val observeRocketLaunchEvents: ObserveRocketLaunchEventsUseCase,
    private val registerRocketLaunchListener: RegisterPickUpListenerUseCase,
    private val unregisterRocketLaunchListener: UnregisterRocketLaunchListenerUseCase
) : AbstractViewModel<RocketLaunchViewModel.State>(State()) {

    init {
        launch {
            observeRocketLaunchEvents().collect {
                state = state.copy(isLaunched = true)
                unregisterRocketLaunchListener()
            }
        }
    }

    fun onGoBack() {
        goBack()
    }

    fun onResume() {
        registerRocketLaunchListener()
    }

    fun onPause() {
        unregisterRocketLaunchListener()
    }

    data class State(
        val isLaunched: Boolean = false,
    ) : AbstractViewModel.State
}