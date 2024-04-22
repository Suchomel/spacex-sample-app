package cz.gemsi.spacex.feature.launch.domain

import cz.gemsi.spacex.core.model.UnitUseCase

class RegisterPickUpListenerUseCase(
    private val pickUpEventController: RocketLaunchController,
) : UnitUseCase<Unit> {

    override fun invoke() {
        pickUpEventController.registerListener()
    }
}