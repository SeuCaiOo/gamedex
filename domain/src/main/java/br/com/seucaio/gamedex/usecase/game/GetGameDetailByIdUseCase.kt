package br.com.seucaio.gamedex.usecase.game

import br.com.seucaio.gamedex.model.game.GameDetail
import br.com.seucaio.gamedex.repository.GamesRepository

class GetGameDetailByIdUseCase(
    val repository: GamesRepository
) {
    suspend operator fun invoke(id: Int): Result<GameDetail> = repository.getById(id)
}
