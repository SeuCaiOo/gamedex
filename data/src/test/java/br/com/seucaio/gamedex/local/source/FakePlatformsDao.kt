package br.com.seucaio.gamedex.local.source

import br.com.seucaio.gamedex.local.database.dao.PlatformsDao
import br.com.seucaio.gamedex.local.database.entity.GamePlatformEntity

class FakePlatformsDao : PlatformsDao {

    private val platforms = mutableListOf<GamePlatformEntity>()

    override suspend fun insertAll(platforms: List<GamePlatformEntity>) {
        this.platforms.addAll(platforms)
    }

    override suspend fun update(platform: GamePlatformEntity) {
        val index = platforms.indexOfFirst { it.platformId == platform.platformId }
        if (index != -1) {
            platforms[index] = platform
        }
    }

    override suspend fun getAll(): List<GamePlatformEntity> {
        return platforms
    }

    override suspend fun getByPlatformId(platformId: Int): GamePlatformEntity? {
        return platforms.find { it.platformId == platformId }
    }

    override suspend fun deleteAll() {
        platforms.clear()
    }
}