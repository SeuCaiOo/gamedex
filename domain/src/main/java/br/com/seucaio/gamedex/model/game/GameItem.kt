package br.com.seucaio.gamedex.model.game

import br.com.seucaio.gamedex.model.data.GameData
import br.com.seucaio.gamedex.model.data.GameDataInfo
import br.com.seucaio.gamedex.util.extension.EMPTY
import br.com.seucaio.gamedex.util.extension.ZERO

data class GameItem(
    override val id: Int,
    override val name: String,
    val imageBackground: String = String.EMPTY,
    val metacritic: Int = Int.ZERO,
    val released: String = String.EMPTY,
    val rating: Double = Double.ZERO,
    val platforms: List<GameDataInfo> = emptyList(),
    val stores: List<GameDataInfo> = emptyList(),
    val genres: List<GameDataInfo> = emptyList(),
    val esrbRating: GameEsrbRatingDetail? = null,
    val shortScreenshots: List<GameScreenshot> = emptyList(),
) : GameData {
    companion object {
        val sampleList = listOf(
            GameItem(
                id = 28637,
                name = "Forza Horizon",
                imageBackground = "https://media.rawg.io/media/games/994/99496806493c2f39b9f191923de2a63b.jpg",
                metacritic = 85,
                released = "2012-10-23",
                rating = 4.36,
                platforms = listOf(
                    GameDataInfo(id = 14, name = "Xbox 360")
                ),
                stores = listOf(
                    GameDataInfo(id = 7, name = "Xbox 360 Store")
                ),
                genres = listOf(
                    GameDataInfo(id = 1, name = "Racing"),
                    GameDataInfo(id = 11, name = "Arcade")
                ),
                esrbRating = GameEsrbRatingDetail(id = 3, name = "Teen"),
                shortScreenshots = GameScreenshot.sampleList
            ),
            GameItem(
                id = 28633,
                name = "Forza Horizon 2",
                imageBackground = "https://media.rawg.io/media/games/9a8/9a8be216c8907959a9984eec3a2cc8d1.jpg",
                metacritic = 86,
                released = "2014-09-30",
                rating = 4.21,
                platforms = listOf(
                    GameDataInfo(id = 1, name = "Xbox One"),
                    GameDataInfo(id = 14, name = "Xbox 360")
                ),
                stores = listOf(
                    GameDataInfo(id = 2, name = "Xbox Store"),
                    GameDataInfo(id = 7, name = "Xbox 360 Store")
                ),
                genres = listOf(
                    GameDataInfo(id = 1, name = "Racing"),
                    GameDataInfo(id = 11, name = "Arcade")
                ),
                esrbRating = GameEsrbRatingDetail(id = 2, name = "Everyone 10+"),
                shortScreenshots = GameScreenshot.sampleList
            ),
            GameItem(
                id = 28435,
                name = "Forza Horizon 2 Presents Fast & Furious",
                imageBackground = "https://media.rawg.io/media/games/89e/89e5503a8f4b5c1b5a0783e4bbab588a.jpg",
                metacritic = 61,
                released = "2015-03-27",
                rating = 3.73,
                platforms = listOf(
                    GameDataInfo(id = 1, name = "Xbox One"),
                    GameDataInfo(id = 14, name = "Xbox 360")
                ),
                stores = listOf(
                    GameDataInfo(id = 2, name = "Xbox Store"),
                    GameDataInfo(id = 7, name = "Xbox 360 Store")
                ),
                genres = listOf(
                    GameDataInfo(id = 1, name = "Racing"),
                    GameDataInfo(id = 4, name = "Action")
                ),
                esrbRating = GameEsrbRatingDetail(id = 2, name = "Everyone 10+"),
                shortScreenshots = GameScreenshot.sampleList
            )
        )
    }
}
