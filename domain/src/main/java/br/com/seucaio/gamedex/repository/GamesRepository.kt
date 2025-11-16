package br.com.seucaio.gamedex.repository

import br.com.seucaio.gamedex.model.game.GameDetail
import br.com.seucaio.gamedex.model.game.GameItem

interface GamesRepository {
    suspend fun searchByPlatform(query: String, platformId: Int): Result<List<GameItem>>
    suspend fun getById(id: Int): Result<GameDetail>
}