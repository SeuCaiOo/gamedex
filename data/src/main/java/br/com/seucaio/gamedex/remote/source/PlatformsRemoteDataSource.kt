package br.com.seucaio.gamedex.remote.source

import br.com.seucaio.gamedex.core.network.ConnectivityChecker
import br.com.seucaio.gamedex.core.error.NetworkErrorHandler.handleNetworkError
import br.com.seucaio.gamedex.remote.dto.GameDataInfoResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListResponse
import br.com.seucaio.gamedex.remote.service.GameDexApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface PlatformsRemoteDataSource {
    suspend fun getAll(): GameDataListResponse
    suspend fun getById(id: Int): GameDataInfoResponse
}

class PlatformsRemoteDataSourceImpl(
    private val apiService: GameDexApiService,
    private val connectivityChecker: ConnectivityChecker,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : PlatformsRemoteDataSource {
    override suspend fun getAll(): GameDataListResponse {
        return safeApiCall { apiService.getPlatforms() }
    }

    override suspend fun getById(id: Int): GameDataInfoResponse {
        return safeApiCall { apiService.getPlatformById(id) }
    }

    // TODO remove duplication
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