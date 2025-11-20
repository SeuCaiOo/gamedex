package br.com.seucaio.gamedex.remote.source

import br.com.seucaio.gamedex.core.error.NetworkErrorHandler.safeApiCall
import br.com.seucaio.gamedex.core.network.ConnectivityChecker
import br.com.seucaio.gamedex.remote.dto.GameDataInfoResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListResponse
import br.com.seucaio.gamedex.remote.service.GameDexApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface PlatformsRemoteDataSource {
    suspend fun getAll(): GameDataListResponse
    suspend fun getById(id: Int): GameDataInfoResponse
}

class PlatformsRemoteDataSourceImpl(
    private val apiService: GameDexApiService,
    private val connectivityChecker: ConnectivityChecker,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : PlatformsRemoteDataSource {

    private val isNetworkAvailable: Boolean
        get() = connectivityChecker.isNetworkAvailable

    override suspend fun getAll(): GameDataListResponse {
        return safeApiCall(
            dispatcher = ioDispatcher,
            onNetworkAvailable = { isNetworkAvailable }
        ) { apiService.getPlatforms() }
    }

    override suspend fun getById(id: Int): GameDataInfoResponse {
        return safeApiCall(
            dispatcher = ioDispatcher,
            onNetworkAvailable = { isNetworkAvailable }
        ) { apiService.getPlatformById(id) }
    }
}
