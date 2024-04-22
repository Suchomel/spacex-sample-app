package cz.gemsi.spacex.feature.detail.presentation

import cz.gemsi.spacex.core.presentation.AbstractViewModel
import cz.gemsi.spacex.library.navigation.domain.GoBackUseCase
import cz.gemsi.spacex.library.navigation.domain.GoToRocketLaunchUseCase
import cz.gemsi.spacex.library.rocket.domain.GetSelectedRocketUseCase
import cz.gemsi.spacex.library.rocket.model.Rocket

class RocketDetailViewModel(
    private val getSelectedRocket: GetSelectedRocketUseCase,
    private val goBack: GoBackUseCase,
    private val goToRocketLaunch: GoToRocketLaunchUseCase,
) : AbstractViewModel<RocketDetailViewModel.State>(State()) {

    init {
        launch {
            val rocket = getSelectedRocket()
            state = state.copy(rocket = rocket)
        }
    }

    fun onGoBack() {
        goBack()
    }

    fun onLaunchClick() {
        goToRocketLaunch()
    }

    data class State(
        val rocket: Rocket? = null
    ) : AbstractViewModel.State
}