package br.com.seucaio.gamedex.remote.service

import br.com.seucaio.gamedex.BuildConfig
import br.com.seucaio.gamedex.remote.dto.list.GameDataInfoResponse
import br.com.seucaio.gamedex.remote.dto.GameDataListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameDexApiService {
    @GET("platforms")
    suspend fun getPlatforms(
        @Query("key") apiKey: String = API_KEY,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 50
    ): GameDataListResponse

    @GET("platforms/{id}")
    suspend fun getPlatformById(
        @Path("id") platformId: Int,
        @Query("key") apiKey: String = API_KEY
    ): GameDataInfoResponse


    @GET("/games")
    suspend fun getGames(@Query("key") apiKey: String)

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
        const val BASE_URL = "https://api.rawg.io/api/"
    }
}