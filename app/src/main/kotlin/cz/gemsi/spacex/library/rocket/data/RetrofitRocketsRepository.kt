package cz.gemsi.spacex.library.rocket.data

import cz.gemsi.spacex.core.model.ErrorResult
import cz.gemsi.spacex.core.model.Result
import cz.gemsi.spacex.library.api.data.SpaceXService
import cz.gemsi.spacex.library.rocket.domain.RemoteRocketsRepository
import cz.gemsi.spacex.library.rocket.model.Rocket

class RetrofitRocketsRepository(
    private val spaceXService: SpaceXService
) : RemoteRocketsRepository {

    override suspend fun fetchRockets(): Result<List<Rocket>> {
        val dto = try {
            spaceXService.getRockets().body()
        } catch (ex: Exception) {
            return Result.error(ErrorResult(message = "Request failed", throwable = ex))
        }

        return dto?.let { rockets ->
            Result.success(rockets.map { it.toModel() })
        } ?: Result.error(ErrorResult(message = "Request failed"))
    }
}