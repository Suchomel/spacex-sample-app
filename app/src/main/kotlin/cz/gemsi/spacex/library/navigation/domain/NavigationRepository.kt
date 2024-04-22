package cz.gemsi.spacex.library.navigation.domain

import cz.gemsi.spacex.library.navigation.model.NavigationEvent
import kotlinx.coroutines.flow.Flow

interface NavigationRepository {
    val navigationEvents: Flow<NavigationEvent>
    fun goBack()
    fun goToRocketDetail()
    fun goToRocketLaunch()
}