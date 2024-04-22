package cz.gemsi.spacex.feature.launch.di

import android.content.Context
import android.hardware.SensorManager
import cz.gemsi.spacex.feature.launch.domain.ObserveRocketLaunchEventsUseCase
import cz.gemsi.spacex.feature.launch.domain.RocketLaunchController
import cz.gemsi.spacex.feature.launch.domain.RegisterPickUpListenerUseCase
import cz.gemsi.spacex.feature.launch.domain.UnregisterRocketLaunchListenerUseCase
import cz.gemsi.spacex.feature.launch.presentation.RocketLaunchViewModel
import cz.gemsi.spacex.feature.launch.system.RocketLaunchDelegate
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val featureLaunchModule = module {
    viewModelOf(::RocketLaunchViewModel)

    factoryOf(::RegisterPickUpListenerUseCase)
    factoryOf(::UnregisterRocketLaunchListenerUseCase)
    factoryOf(::ObserveRocketLaunchEventsUseCase)

    singleOf(::RocketLaunchDelegate) bind RocketLaunchController::class
    single { get<Context>().getSystemService(Context.SENSOR_SERVICE) as SensorManager }
}