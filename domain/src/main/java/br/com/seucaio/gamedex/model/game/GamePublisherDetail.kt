package br.com.seucaio.gamedex.model.game

import br.com.seucaio.gamedex.model.data.GameDataDetail
import br.com.seucaio.gamedex.util.extension.EMPTY

data class GamePublisherDetail(
    override val id: Int,
    override val name: String,
    override val imageBackground: String = String.EMPTY,
    val gamesCount: Int
) : GameDataDetail {
}