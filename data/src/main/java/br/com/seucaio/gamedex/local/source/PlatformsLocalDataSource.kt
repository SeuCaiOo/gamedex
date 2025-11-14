package br.com.seucaio.gamedex.local.source

import br.com.seucaio.gamedex.local.database.GamePlatformEntity
import br.com.seucaio.gamedex.local.database.PlatformsDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface PlatformsLocalDataSource {
    suspend fun getAll(): List<GamePlatformEntity>
    suspend fun getById(id: Int): GamePlatformEntity?
    suspend fun getByPlatformId(platformId: Int): GamePlatformEntity?
    suspend fun clearAndCache(platforms: List<GamePlatformEntity>)
    suspend fun update(platform: GamePlatformEntity)
}

class PlatformsLocalDataSourceImpl(
    private val dao: PlatformsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PlatformsLocalDataSource {
    override suspend fun getAll(): List<GamePlatformEntity> {
        return withContext(ioDispatcher) { dao.getAll() }
    }

    override suspend fun getById(id: Int): GamePlatformEntity? {
        return withContext(ioDispatcher) { dao.getById(id) }
    }

    override suspend fun getByPlatformId(platformId: Int): GamePlatformEntity? {
        return withContext(ioDispatcher) { dao.getByPlatformId(platformId) }
    }

    override suspend fun clearAndCache(platforms: List<GamePlatformEntity>) {
        withContext(ioDispatcher) {
            dao.deleteAll()
            dao.insertAll(platforms)
        }
    }

    override suspend fun update(platform: GamePlatformEntity) {
        return withContext(ioDispatcher) { dao.update(platform) }
    }
}