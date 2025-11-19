package br.com.seucaio.gamedex.model.game

import br.com.seucaio.gamedex.model.data.GameDataDetail
import br.com.seucaio.gamedex.util.extension.EMPTY

data class GamePublisherDetail(
    override val id: Int,
    override val name: String,
    override val imageBackground: String = String.EMPTY,
    val gamesCount: Int
) : GameDataDetail {
    companion object {
        val sampleList = listOf(
            GamePublisherDetail(
                id = 2150,
                name = "Capcom",
                imageBackground = "https://media.rawg.io/media/games/295/295eb868c241e6ad32ac033b8e6a2ede.jpg",
                gamesCount = 632
            ),
            GamePublisherDetail(
                id = 2155,
                name = "Rockstar Games",
                imageBackground = "https://media.rawg.io/media/games/769/769b7f0f609f44bac86f2c791fee21dd.jpg",
                gamesCount = 81
            ),
            GamePublisherDetail(
                id = 4879,
                name = "1C-SoftClub",
                imageBackground = "https://media.rawg.io/media/games/7ea/7ea3575b85ee2a8097b99583aa7c86f3.jpg",
                gamesCount = 51
            )
        )
    }
}
