package br.com.seucaio.gamedex.remote.source

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
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : PlatformsRemoteDataSource {
    override suspend fun getAll(): GameDataListResponse {
        return withContext(ioDispatcher) { apiService.getPlatforms() }
    }

    override suspend fun getById(id: Int): GameDataInfoResponse {
        return withContext(ioDispatcher) { apiService.getPlatformById(id) }
    }
}