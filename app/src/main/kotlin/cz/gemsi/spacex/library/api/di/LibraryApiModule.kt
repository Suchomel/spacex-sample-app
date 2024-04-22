package cz.gemsi.spacex.library.api.di

import com.google.gson.GsonBuilder
import cz.gemsi.spacex.library.api.data.SpaceXService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val libraryApiModule = module {
    single { provideSpaceXService() }
}

private fun provideSpaceXService(): SpaceXService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        })
        .build()

    val gson = GsonBuilder()
        .setLenient()
        .create()

    return retrofit2.Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(SpaceXService.API_URL)
        .build()
        .create(SpaceXService::class.java)
}