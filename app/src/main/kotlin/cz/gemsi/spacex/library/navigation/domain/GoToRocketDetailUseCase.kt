package cz.gemsi.spacex.library.navigation.domain

import cz.gemsi.spacex.core.model.UseCase
import cz.gemsi.spacex.library.rocket.domain.SetSelectedRocketUseCase
import cz.gemsi.spacex.library.rocket.model.Rocket

class GoToRocketDetailUseCase(
    private val setSelectedRocket: SetSelectedRocketUseCase,
    private val navigationRepository: NavigationRepository,
) : UseCase<Rocket, Unit> {

    override fun invoke(params: Rocket) {
        setSelectedRocket(params)
        navigationRepository.goToRocketDetail()
    }
}