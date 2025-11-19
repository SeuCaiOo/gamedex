package br.com.seucaio.gamedex.local.source

import br.com.seucaio.gamedex.local.database.dao.PlatformsDao
import br.com.seucaio.gamedex.local.database.dao.TopGamesDao
import br.com.seucaio.gamedex.local.database.entity.GamePlatformEntity
import br.com.seucaio.gamedex.local.database.entity.TopGameEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface PlatformsLocalDataSource {
    suspend fun getAll(): List<GamePlatformEntity>
    suspend fun getByPlatformId(platformId: Int): GamePlatformEntity?
    suspend fun clearAndCache(platforms: List<GamePlatformEntity>)
    suspend fun updatePlatform(platform: GamePlatformEntity)
    suspend fun clearAndCacheTopGames(topGames: List<TopGameEntity>)
    suspend fun getTopGamesByPlatform(platformId: Int): List<TopGameEntity>
}

class PlatformsLocalDataSourceImpl(
    private val dao: PlatformsDao,
    private val topGamesDao: TopGamesDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PlatformsLocalDataSource {
    override suspend fun getAll(): List<GamePlatformEntity> {
        return withContext(ioDispatcher) { dao.getAll() }
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

    override suspend fun updatePlatform(platform: GamePlatformEntity) {
        return withContext(ioDispatcher) { dao.update(platform) }
    }

    override suspend fun clearAndCacheTopGames(topGames: List<TopGameEntity>) {
        withContext(ioDispatcher) {
            topGamesDao.deleteAll()
            topGamesDao.insertAll(topGames)
        }
    }

    override suspend fun getTopGamesByPlatform(platformId: Int): List<TopGameEntity> {
        return withContext(ioDispatcher) { topGamesDao.getByPlatformId(platformId) }
    }
}
