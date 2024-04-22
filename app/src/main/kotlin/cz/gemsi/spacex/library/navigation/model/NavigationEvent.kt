package cz.gemsi.spacex.library.navigation.model

sealed class NavigationEvent {

    data class GoTo(val route: Route) : NavigationEvent()

    object GoBack : NavigationEvent()
}