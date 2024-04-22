package cz.gemsi.spacex.app.di

import android.content.Context
import cz.gemsi.spacex.app.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    viewModelOf(::MainViewModel)

    factory { get<Context>().resources }
}