package br.com.seucaio.gamedex.model.game

import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.util.extension.EMPTY

data class GamePlatformDetailInfo(
    val platformItem: GamePlatform,
    val releasedAt: String = String.EMPTY,
    val requirements: PlatformRequirements? =null,
) {
    data class PlatformRequirements(
        val minimum: String = String.EMPTY,
        val recommended: String = String.EMPTY,
    )
}

