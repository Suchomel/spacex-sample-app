package cz.gemsi.spacex.feature.list.presentation

import cz.gemsi.spacex.core.presentation.AbstractViewModel
import cz.gemsi.spacex.library.navigation.domain.GoToRocketDetailUseCase
import cz.gemsi.spacex.library.rocket.Rocket

class RocketListViewModel(
    private val goToRocketDetail: GoToRocketDetailUseCase,
) : AbstractViewModel<RocketListViewModel.State>(State()) {

    init {
        val rockets = listOf(
            Rocket("Rocket 1", "10. 4. 2023"),
            Rocket("Rocket 2", "10. 4. 2023"),
            Rocket("Rocket 3", "10. 4. 2023"),
        )

        state = state.copy(rockets = rockets)
    }

    fun onRocketClick(rocket: Rocket) {
        goToRocketDetail(rocket)
    }

    data class State(
        val rockets: List<Rocket> = listOf()
    ) : AbstractViewModel.State
}