package cz.gemsi.spacex.app.di

import android.app.Application
import cz.gemsi.spacex.feature.detail.di.featureDetailModule
import cz.gemsi.spacex.feature.list.di.featureListModule
import cz.gemsi.spacex.library.api.di.libraryApiModule
import cz.gemsi.spacex.library.navigation.di.libraryNavigationModule
import cz.gemsi.spacex.library.rocket.di.libraryRocketModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(modules)
        }
    }

    private val modules = listOf(
        appModule,
        featureListModule,
        libraryApiModule,
        libraryNavigationModule,
        featureDetailModule,
        libraryRocketModule,
    )
}