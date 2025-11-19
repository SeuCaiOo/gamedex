package br.com.seucaio.gamedex.usecase.platform

import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.repository.PlatformsRepository

class GetPlatformsUseCase(
    val repository: PlatformsRepository
) {
    suspend operator fun invoke(): Result<List<GamePlatform>> = repository.getAll()
}
