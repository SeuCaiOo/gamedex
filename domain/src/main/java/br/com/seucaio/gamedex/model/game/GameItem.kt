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
) : GameData{
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
                shortScreenshots = listOf(
                    GameScreenshot(id = -1, image = "https://media.rawg.io/media/games/994/99496806493c2f39b9f191923de2a63b.jpg"),
                    GameScreenshot(id = 2021137, image = "https://media.rawg.io/media/screenshots/4f0/4f08c34cdee8f47944a169f80f34ebcd.jpg"),
                    GameScreenshot(id = 2021139, image = "https://media.rawg.io/media/screenshots/a23/a23b55359a952efd51025616e19820df.jpg"),
                    GameScreenshot(id = 2021140, image = "https://media.rawg.io/media/screenshots/623/6230ff9a1dfd3f6235687f13e752ba84.jpg"),
                    GameScreenshot(id = 2021141, image = "https://media.rawg.io/media/screenshots/a94/a94c08a4411580af9b295609a51a5384.jpg"),
                    GameScreenshot(id = 279011, image = "https://media.rawg.io/media/screenshots/efa/efa1af5a9b0fc4c22fc089572e568660.jpg"),
                    GameScreenshot(id = 279012, image = "https://media.rawg.io/media/screenshots/ddb/ddb6e708570bb8e9dad0cb11d56d880b.jpg")
                )
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
                shortScreenshots = listOf(
                    GameScreenshot(id = -1, image = "https://media.rawg.io/media/games/9a8/9a8be216c8907959a9984eec3a2cc8d1.jpg"),
                    GameScreenshot(id = 806274, image = "https://media.rawg.io/media/screenshots/c5c/c5cb7742c780ecec82b6752a87c17adc.jpg"),
                    GameScreenshot(id = 806275, image = "https://media.rawg.io/media/screenshots/b54/b54e70665ca52013d66481b0795defe0_n9zyXmg.jpg"),
                    GameScreenshot(id = 806276, image = "https://media.rawg.io/media/screenshots/258/258098804b3463743e0fd7e88c84ab2e.jpg"),
                    GameScreenshot(id = 806277, image = "https://media.rawg.io/media/screenshots/9e5/9e59319e9ef9ebda6e3c348435229f32.jpg")
                )
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
                shortScreenshots = listOf(
                    GameScreenshot(id = -1, image = "https://media.rawg.io/media/games/89e/89e5503a8f4b5c1b5a0783e4bbab588a.jpg"),
                    GameScreenshot(id = 806270, image = "https://media.rawg.io/media/screenshots/d2d/d2d7ebffadb804242474c13dee6ce84a.jpg"),
                    GameScreenshot(id = 806271, image = "https://media.rawg.io/media/screenshots/b00/b0097a14b70a121fd86a4f17035b229c.jpg"),
                    GameScreenshot(id = 806272, image = "https://media.rawg.io/media/screenshots/7bd/7bd54ebce570e8c270d7f77f753994bc.jpg"),
                    GameScreenshot(id = 806273, image = "https://media.rawg.io/media/screenshots/d28/d285ef21d788f5bd3912e263945aefd3.jpg")
                )
            )
        )
    }
}