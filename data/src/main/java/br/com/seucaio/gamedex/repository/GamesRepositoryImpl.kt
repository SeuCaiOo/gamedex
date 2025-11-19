package br.com.seucaio.gamedex.repository

import br.com.seucaio.gamedex.mapper.GamesMapper.toDomain
import br.com.seucaio.gamedex.model.game.GameDetail
import br.com.seucaio.gamedex.model.game.GameItem
import br.com.seucaio.gamedex.remote.source.GamesRemoteDatSource

class GamesRepositoryImpl(
    private val remoteDataSource: GamesRemoteDatSource
) : GamesRepository {
    override suspend fun searchByPlatform(
        query: String,
        platformId: Int
    ): Result<List<GameItem>> {
        return try {
            val response = remoteDataSource.searchByPlatform(query, platformId)
            Result.success(response.results.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getById(id: Int): Result<GameDetail> {
        return try {
            val response = remoteDataSource.getById(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
