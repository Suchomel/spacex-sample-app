package cz.gemsi.spacex.library.rocket.domain

import cz.gemsi.spacex.core.model.UnitUseCase
import cz.gemsi.spacex.library.rocket.model.Rocket

class GetSelectedRocketUseCase(
    private val localRocketsRepository: LocalRocketsRepository
) : UnitUseCase<Rocket?> {

    override fun invoke(): Rocket? {
        return localRocketsRepository.selectedRocketDetail
    }
}