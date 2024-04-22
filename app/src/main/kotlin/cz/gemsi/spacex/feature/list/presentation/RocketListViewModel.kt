package cz.gemsi.spacex.feature.list.presentation

import cz.gemsi.spacex.core.presentation.AbstractViewModel
import cz.gemsi.spacex.library.navigation.domain.GoToRocketDetailUseCase
import cz.gemsi.spacex.library.rocket.domain.FetchRocketsUseCase
import cz.gemsi.spacex.library.rocket.model.Rocket

class RocketListViewModel(
    private val goToRocketDetail: GoToRocketDetailUseCase,
    private val fetchRockets: FetchRocketsUseCase,
) : AbstractViewModel<RocketListViewModel.State>(State()) {

    init {
        launch {
            val rockets = fetchRockets().getOrNull()
            state = state.copy(rockets = rockets ?: state.rockets)
        }
    }

    fun onRocketClick(rocket: Rocket) {
        goToRocketDetail(rocket)
    }

    data class State(
        val rockets: List<Rocket> = listOf()
    ) : AbstractViewModel.State
}