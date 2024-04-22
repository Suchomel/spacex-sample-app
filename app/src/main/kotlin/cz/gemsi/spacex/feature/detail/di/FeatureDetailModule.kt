package cz.gemsi.spacex.feature.detail.di

import cz.gemsi.spacex.feature.detail.presentation.RocketDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureDetailModule = module {
    viewModelOf(::RocketDetailViewModel)
}