package cz.gemsi.spacex.feature.list.di

import cz.gemsi.spacex.feature.list.presentation.RocketListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureListModule = module {
    viewModelOf(::RocketListViewModel)
}