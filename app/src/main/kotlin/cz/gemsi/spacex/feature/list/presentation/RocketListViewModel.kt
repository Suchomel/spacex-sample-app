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
        onRefreshData()
    }

    fun onRefreshData() {
        launch {
            state = state.copy(isLoading = true)
            val rockets = fetchRockets()
            state = state.copy(
                rockets = rockets.getOrNull() ?: state.rockets,
                isLoading = false,
                isError = rockets.isError(),
            )
        }
    }

    fun onRocketClick(rocket: Rocket) {
        goToRocketDetail(rocket)
    }

    data class State(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val rockets: List<Rocket> = listOf()
    ) : AbstractViewModel.State
}