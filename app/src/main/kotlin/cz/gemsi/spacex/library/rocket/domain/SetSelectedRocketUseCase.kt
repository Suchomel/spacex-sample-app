package cz.gemsi.spacex.library.rocket.domain

import cz.gemsi.spacex.core.model.UseCase
import cz.gemsi.spacex.library.rocket.model.Rocket

class SetSelectedRocketUseCase(
    private val localRocketsRepository: LocalRocketsRepository
) : UseCase<Rocket, Unit> {

    override fun invoke(params: Rocket) {
        localRocketsRepository.selectedRocketDetail = params
    }
}