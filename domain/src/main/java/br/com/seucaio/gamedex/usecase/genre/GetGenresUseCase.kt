package br.com.seucaio.gamedex.usecase.genre

import br.com.seucaio.gamedex.model.genre.GameGenre
import br.com.seucaio.gamedex.repository.GenresRepository

class GetGenresUseCase(
    val repository: GenresRepository
) {
    suspend operator fun invoke(): Result<List<GameGenre>> = repository.getAll()
}
