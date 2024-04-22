package cz.gemsi.spacex.app.di

import android.content.Context
import org.koin.dsl.module


val appModule = module {
    factory { get<Context>().resources }
}