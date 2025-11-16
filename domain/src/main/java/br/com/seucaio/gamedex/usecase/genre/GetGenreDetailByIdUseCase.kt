package br.com.seucaio.gamedex.usecase.genre

import br.com.seucaio.gamedex.model.genre.GameGenreDetail
import br.com.seucaio.gamedex.repository.GenresRepository

class GetGenreDetailByIdUseCase(
    val repository: GenresRepository
) {
    suspend operator fun invoke(id: Int): Result<GameGenreDetail> = repository.getById(id)
}