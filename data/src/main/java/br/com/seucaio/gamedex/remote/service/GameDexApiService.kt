package br.com.seucaio.gamedex.remote.service

import br.com.seucaio.gamedex.data.BuildConfig
import br.com.seucaio.gamedex.remote.dto.GameDataInfoResponse
import br.com.seucaio.gamedex.remote.dto.game.GameDetailResponse
import br.com.seucaio.gamedex.remote.dto.game.GameItemResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListResponse
import br.com.seucaio.gamedex.remote.dto.list.GameListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameDexApiService {
    @GET("platforms")
    suspend fun getPlatforms(
        @Query("key") apiKey: String = API_KEY,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 40
    ): GameDataListResponse

    @GET("platforms/{id}")
    suspend fun getPlatformById(
        @Path("id") platformId: Int,
        @Query("key") apiKey: String = API_KEY
    ): GameDataInfoResponse

    @GET("games")
    suspend fun searchGamesByPlatform(
        @Query("search") query: String,
        @Query("platforms") singlePlatformId: Int,
        @Query("key") apiKey: String = API_KEY,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 40,
        @Query("search_exact") searchExact: Boolean = true,
        @Query("search_precise") searchPrecise: Boolean = true,
    ): GameListResponse<GameItemResponse>

    @GET("games/{id}")
    suspend fun getGameById(
        @Path("id") gameId: Int,
        @Query("key") apiKey: String = API_KEY
    ): GameDetailResponse

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
        const val BASE_URL = "https://api.rawg.io/api/"
    }
}
