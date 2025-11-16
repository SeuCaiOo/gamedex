package br.com.seucaio.gamedex.model.game

import br.com.seucaio.gamedex.model.data.GameData
import br.com.seucaio.gamedex.util.extension.EMPTY
import br.com.seucaio.gamedex.util.extension.ZERO

class GameItem(
    override val id: Int,
    override val name: String,
    val imageBackground: String = String.EMPTY,
    val metacritic: Int = Int.ZERO,
    val released: String = String.EMPTY,
    val rating: Double = Double.ZERO,
    val platforms: List<GameData> = emptyList(),
    val stores: List<GameData> = emptyList(),
    val genres: List<GameData> = emptyList(),
    val esrbRating: GameData? = null,
    val shortScreenshots: List<GameScreenshot> = emptyList(),
) : GameData {
}