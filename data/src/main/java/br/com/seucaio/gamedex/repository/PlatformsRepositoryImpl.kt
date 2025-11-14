package br.com.seucaio.gamedex.repository

import br.com.seucaio.gamedex.local.database.GamePlatformEntity
import br.com.seucaio.gamedex.local.source.PlatformsLocalDataSource
import br.com.seucaio.gamedex.mapper.GameDataMapper.toPlatformDetailDomain
import br.com.seucaio.gamedex.mapper.GameDataMapper.toPlatformDomain
import br.com.seucaio.gamedex.mapper.PlatformsMapper.toDomain
import br.com.seucaio.gamedex.mapper.PlatformsMapper.toEntity
import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.model.platform.GamePlatformDetail
import br.com.seucaio.gamedex.remote.source.PlatformsRemoteDataSource

class PlatformsRepositoryImpl(
    private val remoteDataSource: PlatformsRemoteDataSource,
    private val localDataSource: PlatformsLocalDataSource
) : PlatformsRepository {
    override suspend fun getAll(): Result<List<GamePlatform>> {
        return try {
            val localData: List<GamePlatform> = localDataSource.getAll().toDomain()
            val remoteData: List<GamePlatform> =
                remoteDataSource.getAll().results.toPlatformDomain()
            if (localData.isEmpty()) localDataSource.clearAndCache(remoteData.toEntity())
            Result.success(remoteData)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getById(id: Int): Result<GamePlatformDetail> {
        return try {
            val platformEntity: GamePlatformEntity? = localDataSource.getById(id)
            val remoteData: GamePlatformDetail =
                remoteDataSource.getById(id).toPlatformDetailDomain()

            platformEntity?.let { entity ->
                if (entity.description.isBlank()) {
                    localDataSource.update(entity.copy(description = remoteData.description))
                }
            }
            Result.success(remoteData)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}