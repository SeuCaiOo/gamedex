package br.com.seucaio.gamedex.repository

import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.model.platform.GamePlatformDetail

interface PlatformsRepository {
    suspend fun getAll(): Result<List<GamePlatform>>
    suspend fun getById(id: Int): Result<GamePlatformDetail>
}
