package br.com.seucaio.gamedex.repository

import br.com.seucaio.gamedex.model.genre.GameGenre
import br.com.seucaio.gamedex.model.genre.GameGenreDetail

interface GenresRepository {
    suspend fun getAll(): Result<List<GameGenre>>
    suspend fun getById(id: Int): Result<GameGenreDetail>
}