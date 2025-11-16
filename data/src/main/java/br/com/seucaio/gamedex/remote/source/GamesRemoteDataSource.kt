package br.com.seucaio.gamedex.remote.source

import br.com.seucaio.gamedex.core.error.NetworkErrorHandler.handleNetworkError
import br.com.seucaio.gamedex.core.network.ConnectivityChecker
import br.com.seucaio.gamedex.remote.dto.game.GameDetailResponse
import br.com.seucaio.gamedex.remote.dto.game.GameItemResponse
import br.com.seucaio.gamedex.remote.dto.list.GameListResponse
import br.com.seucaio.gamedex.remote.service.GameDexApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface GamesRemoteDatSource {
    suspend fun searchByPlatform(query: String, platformId: Int): GameListResponse<GameItemResponse>
    suspend fun getById(id: Int): GameDetailResponse
}

class GamesRemoteDataSourceImpl(
    private val apiService: GameDexApiService,
    private val connectivityChecker: ConnectivityChecker,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : GamesRemoteDatSource {
    override suspend fun searchByPlatform(
        query: String,
        platformId: Int
    ): GameListResponse<GameItemResponse> {
        return safeApiCall { apiService.searchGamesByPlatform(query, platformId) }
    }

    override suspend fun getById(id: Int): GameDetailResponse {
        return safeApiCall { apiService.getGameById(id) }
    }

    private suspend fun <T> safeApiCall(block: suspend () -> T): T {
        return withContext(ioDispatcher) {
            try {
                block()
            } catch (e: Exception) {
                throw e.handleNetworkError(connectivityChecker.isNetworkAvailable)
            }
        }
    }

}