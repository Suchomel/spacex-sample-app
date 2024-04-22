package cz.gemsi.spacex.library.navigation.domain

import cz.gemsi.spacex.core.model.UnitUseCase
import cz.gemsi.spacex.library.navigation.model.NavigationEvent
import kotlinx.coroutines.flow.Flow

class ObserveNavigationEventsUseCase(
    private val navigationRepository: NavigationRepository
) : UnitUseCase<Flow<NavigationEvent>> {

    override fun invoke(): Flow<NavigationEvent> {
        return navigationRepository.navigationEvents
    }
}