package cz.gemsi.spacex.library.api.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXService {
    companion object {
        const val API_URL = "https://api.spacexdata.com/v3/"
    }

    @GET("rockets")
    suspend fun getRockets(): Response<RocketsDto>
}