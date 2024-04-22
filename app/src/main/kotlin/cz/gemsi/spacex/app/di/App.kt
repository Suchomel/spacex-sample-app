package cz.gemsi.spacex.app.di

import android.app.Application
import cz.gemsi.spacex.feature.list.di.featureListModule
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
    )
}