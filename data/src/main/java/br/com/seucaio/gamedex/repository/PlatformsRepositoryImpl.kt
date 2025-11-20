package br.com.seucaio.gamedex.repository

import br.com.seucaio.gamedex.local.database.entity.GamePlatformEntity
import br.com.seucaio.gamedex.local.database.entity.TopGameEntity
import br.com.seucaio.gamedex.local.source.PlatformsLocalDataSource
import br.com.seucaio.gamedex.mapper.GameDataMapper.toPlatformDetailDomain
import br.com.seucaio.gamedex.mapper.GameDataMapper.toPlatformDomain
import br.com.seucaio.gamedex.mapper.GameDataMapper.toTopGameEntity
import br.com.seucaio.gamedex.mapper.PlatformsMapper.toDetailDomain
import br.com.seucaio.gamedex.mapper.PlatformsMapper.toDomain
import br.com.seucaio.gamedex.mapper.PlatformsMapper.toEntity
import br.com.seucaio.gamedex.mapper.TopGameMapper.toDomain
import br.com.seucaio.gamedex.model.data.TopGameData
import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.model.platform.GamePlatformDetail
import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoResponse
import br.com.seucaio.gamedex.remote.source.PlatformsRemoteDataSource

class PlatformsRepositoryImpl(
    private val remoteDataSource: PlatformsRemoteDataSource,
    private val localDataSource: PlatformsLocalDataSource
) : PlatformsRepository {
    override suspend fun getAll(): Result<List<GamePlatform>> {
        return runCatching { getSuccessListGamePlatform() }
    }

    private suspend fun getSuccessListGamePlatform(): List<GamePlatform> {
        val localPlatforms: List<GamePlatformEntity> = localDataSource.getAll()
        if (localPlatforms.isNotEmpty()) return localPlatforms.toDomain()

        val remoteResults: List<GameDataListInfoResponse> = remoteDataSource.getAll().results
        val remotePlatforms: List<GamePlatform> = remoteResults.toPlatformDomain()

        localDataSource.clearAndCache(remotePlatforms.toEntity())
        saveCacheTopGames(remoteResults)

        return remotePlatforms
    }

    private suspend fun saveCacheTopGames(remoteResults: List<GameDataListInfoResponse>) {
        val topGamesEntity = mutableListOf<TopGameEntity>()
        remoteResults.forEach { info ->
            info.games?.forEach { topGamesEntity.add(it.toTopGameEntity(platformId = info.id)) }
        }
        localDataSource.clearAndCacheTopGames(topGamesEntity)
    }

    override suspend fun getById(id: Int): Result<GamePlatformDetail> {
        return runCatching { getSuccessPlatformDetail(id) }
    }

    private suspend fun getSuccessPlatformDetail(id: Int): GamePlatformDetail {
        val platformEntity: GamePlatformEntity? = localDataSource.getByPlatformId(id)
        val topGamesCache: List<TopGameData> = getTopGamesPlatformCache(id).toDomain()

        if (platformEntity != null && platformEntity.description.isNotBlank()) {
            return platformEntity.toDetailDomain(topGamesCache)
        }

        val remotePlatformDetail: GamePlatformDetail =
            remoteDataSource.getById(id).toPlatformDetailDomain(topGamesCache)

        val entityToUpdate: GamePlatformEntity? = localDataSource.getByPlatformId(id)
        entityToUpdate?.let {
            localDataSource.updatePlatform(it.setDescription(remotePlatformDetail.description))
        }
        return remotePlatformDetail
    }

    private suspend fun getTopGamesPlatformCache(platformId: Int) =
        localDataSource.getTopGamesByPlatform(platformId)
}
