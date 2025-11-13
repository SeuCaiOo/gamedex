package br.com.seucaio.gamedex.usecase.platform

import br.com.seucaio.gamedex.model.platform.GamePlatformDetail
import br.com.seucaio.gamedex.repository.PlatformsRepository

class GetPlatformDetailByIdUseCase(
    val repository: PlatformsRepository
) {
    suspend operator fun invoke(id: Int): Result<GamePlatformDetail> = repository.getById(id)
}