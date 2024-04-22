package cz.gemsi.spacex.library.rocket.domain

import cz.gemsi.spacex.core.model.Result
import cz.gemsi.spacex.core.model.SuspendResultUnitUseCase
import cz.gemsi.spacex.library.rocket.model.Rocket

class FetchRocketsUseCase(
    private val remoteRocketsRepository: RemoteRocketsRepository
) : SuspendResultUnitUseCase<List<Rocket>> {

    override suspend fun invoke(): Result<List<Rocket>> {
        return remoteRocketsRepository.fetchRockets()
    }
}