package br.com.seucaio.gamedex.usecase.game

import br.com.seucaio.gamedex.model.game.GameItem
import br.com.seucaio.gamedex.repository.GamesRepository

class GetGamesByPlatformByIdUseCase(
    val repository: GamesRepository
) {
    suspend operator fun invoke(
        query: String,
        platformId: Int
    ): Result<List<GameItem>> = repository.searchByPlatform(
        query = query,
        platformId = platformId
    )
}
