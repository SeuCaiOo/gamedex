package br.com.seucaio.gamedex.repository

import br.com.seucaio.gamedex.mapper.GameDataMapper.toPlatformDetailDomain
import br.com.seucaio.gamedex.mapper.GameDataMapper.toPlatformDomain
import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.model.platform.GamePlatformDetail
import br.com.seucaio.gamedex.remote.source.PlatformsRemoteDataSource

class PlatformsRepositoryImpl(
    private val remoteDataSource: PlatformsRemoteDataSource,
) : PlatformsRepository {
    override suspend fun getAll(): Result<List<GamePlatform>> {
        return try {
            val remoteData: List<GamePlatform> =
                remoteDataSource.getAll().results.toPlatformDomain()
            Result.success(remoteData)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getById(id: Int): Result<GamePlatformDetail> {
        return try {
            val remoteData: GamePlatformDetail =
                remoteDataSource.getById(id).toPlatformDetailDomain()
            Result.success(remoteData)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}