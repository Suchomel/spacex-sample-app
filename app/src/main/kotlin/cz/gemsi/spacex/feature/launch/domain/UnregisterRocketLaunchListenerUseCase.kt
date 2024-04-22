package cz.gemsi.spacex.feature.launch.domain

import cz.gemsi.spacex.core.model.UnitUseCase

class UnregisterRocketLaunchListenerUseCase(
    private val rocketLaunchController: RocketLaunchController,
) : UnitUseCase<Unit> {

    override fun invoke() {
        rocketLaunchController.unregisterListener()
    }
}