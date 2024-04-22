package cz.gemsi.spacex.library.rocket.di

import cz.gemsi.spacex.library.rocket.data.MemoryRocketsRepository
import cz.gemsi.spacex.library.rocket.data.RetrofitRocketsRepository
import cz.gemsi.spacex.library.rocket.domain.FetchRocketsUseCase
import cz.gemsi.spacex.library.rocket.domain.GetSelectedRocketUseCase
import cz.gemsi.spacex.library.rocket.domain.LocalRocketsRepository
import cz.gemsi.spacex.library.rocket.domain.RemoteRocketsRepository
import cz.gemsi.spacex.library.rocket.domain.SetSelectedRocketUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val libraryRocketModule = module {
    factoryOf(::FetchRocketsUseCase)
    factoryOf(::SetSelectedRocketUseCase)
    factoryOf(::GetSelectedRocketUseCase)

    singleOf(::RetrofitRocketsRepository) bind RemoteRocketsRepository::class
    singleOf(::MemoryRocketsRepository) bind LocalRocketsRepository::class
}