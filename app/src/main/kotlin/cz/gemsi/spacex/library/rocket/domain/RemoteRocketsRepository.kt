package cz.gemsi.spacex.library.rocket.domain

import cz.gemsi.spacex.core.model.Result
import cz.gemsi.spacex.library.rocket.model.Rocket

interface RemoteRocketsRepository {
    suspend fun fetchRockets(): Result<List<Rocket>>
}