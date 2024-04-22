package cz.gemsi.spacex.library.navigation.data

import cz.gemsi.spacex.library.navigation.domain.NavigationRepository
import cz.gemsi.spacex.library.navigation.model.NavigationEvent
import cz.gemsi.spacex.library.navigation.model.Route
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MainNavigationRepository : NavigationRepository {

    private val _navigationEvents =
        MutableSharedFlow<NavigationEvent>(replay = 1, extraBufferCapacity = 1)
    override val navigationEvents: Flow<NavigationEvent> = _navigationEvents.asSharedFlow()

    override fun goBack() {
        goTo(NavigationEvent.GoBack)
    }

    override fun goToRocketDetail() {
        goTo(NavigationEvent.GoTo(Route.RocketDetail))
    }

    override fun goToRocketLaunch() {
        goTo(NavigationEvent.GoTo(Route.RocketLaunch))
    }

    private fun goTo(navigationEvent: NavigationEvent) {
        _navigationEvents.tryEmit(navigationEvent)
    }
}