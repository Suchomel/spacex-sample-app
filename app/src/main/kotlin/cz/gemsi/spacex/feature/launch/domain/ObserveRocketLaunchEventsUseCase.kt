package cz.gemsi.spacex.feature.launch.domain

import cz.gemsi.spacex.core.model.UnitUseCase
import kotlinx.coroutines.flow.Flow

class ObserveRocketLaunchEventsUseCase(
    private val pickUpEventController: RocketLaunchController,
) : UnitUseCase<Flow<Unit>> {

    override fun invoke(): Flow<Unit> {
        return pickUpEventController.observePickUpEvent()
    }
}