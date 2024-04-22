package cz.gemsi.spacex.library.navigation.domain

import cz.gemsi.spacex.core.model.UseCase
import cz.gemsi.spacex.library.rocket.Rocket

class GoToRocketDetailUseCase(
    private val navigationRepository: NavigationRepository
) : UseCase<Rocket, Unit> {

    override fun invoke(params: Rocket) {
        navigationRepository.goToRocketDetail()
    }
}