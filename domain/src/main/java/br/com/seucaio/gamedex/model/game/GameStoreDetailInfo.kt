package br.com.seucaio.gamedex.model.game

import br.com.seucaio.gamedex.model.data.GameData
import br.com.seucaio.gamedex.util.extension.EMPTY
import br.com.seucaio.gamedex.util.extension.ZERO

data class GameStoreDetailInfo(
    val id: Int,
    val storeItem: StoreItem,
) {
    data class StoreItem(
        override val id: Int,
        override val name: String,
        val domain: String? = null,
        val gamesCount: Int = Int.ZERO,
        val imageBackground: String = String.EMPTY,
    ) : GameData
}
