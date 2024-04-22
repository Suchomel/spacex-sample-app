package cz.gemsi.spacex.library.navigation.di

import cz.gemsi.spacex.library.navigation.data.MainNavigationRepository
import cz.gemsi.spacex.library.navigation.domain.GoBackUseCase
import cz.gemsi.spacex.library.navigation.domain.GoToRocketDetailUseCase
import cz.gemsi.spacex.library.navigation.domain.GoToRocketLaunchUseCase
import cz.gemsi.spacex.library.navigation.domain.NavigationRepository
import cz.gemsi.spacex.library.navigation.domain.ObserveNavigationEventsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val libraryNavigationModule = module {
    singleOf(::MainNavigationRepository) bind NavigationRepository::class

    factoryOf(::ObserveNavigationEventsUseCase)
    factoryOf(::GoBackUseCase)
    factoryOf(::GoToRocketDetailUseCase)
    factoryOf(::GoToRocketLaunchUseCase)
}