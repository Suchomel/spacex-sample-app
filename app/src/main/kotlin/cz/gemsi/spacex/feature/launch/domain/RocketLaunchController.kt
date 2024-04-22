package cz.gemsi.spacex.feature.launch.domain

import kotlinx.coroutines.flow.Flow

interface RocketLaunchController {

    fun observePickUpEvent(): Flow<Unit>
    fun registerListener()
    fun unregisterListener()
}