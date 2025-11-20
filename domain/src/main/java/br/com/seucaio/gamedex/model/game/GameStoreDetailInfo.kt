package br.com.seucaio.gamedex.model.game

import br.com.seucaio.gamedex.model.data.GameData
import br.com.seucaio.gamedex.util.extension.EMPTY
import br.com.seucaio.gamedex.util.extension.ZERO

data class GameStoreDetailInfo(
    val id: Int,
    val storeItem: StoreItem,
) {
    companion object {
        val sampleList = listOf(
            GameStoreDetailInfo(
                id = 958554,
                storeItem = StoreItem(
                    id = 11,
                    name = "Epic Games",
                    domain = "epicgames.com",
                    gamesCount = 1424,
                    imageBackground = "https://media.rawg.io/media/games/8cc/8cce7c0e99dcc43d66c8efd42f9d03e3.jpg"
                )
            ),
            GameStoreDetailInfo(
                id = 3159,
                storeItem = StoreItem(
                    id = 3,
                    name = "PlayStation Store",
                    domain = "store.playstation.com",
                    gamesCount = 8061,
                    imageBackground = "https://media.rawg.io/media/games/310/3106b0e012271c5ffb16497b070be739.jpg"
                )
            ),
            GameStoreDetailInfo(
                id = 466983,
                storeItem = StoreItem(
                    id = 2,
                    name = "Xbox Store",
                    domain = "microsoft.com",
                    gamesCount = 4930,
                    imageBackground = "https://media.rawg.io/media/games/4a0/4a0a1316102366260e6f38fd2a9cfdce.jpg"
                )
            ),
            GameStoreDetailInfo(
                id = 12383,
                storeItem = StoreItem(
                    id = 1,
                    name = "Steam",
                    domain = "store.steampowered.com",
                    gamesCount = 121767,
                    imageBackground = "https://media.rawg.io/media/games/f46/f466571d536f2e3ea9e815ad17177501.jpg"
                )
            ),
            GameStoreDetailInfo(
                id = 33623,
                storeItem = StoreItem(
                    id = 7,
                    name = "Xbox 360 Store",
                    domain = "marketplace.xbox.com",
                    gamesCount = 1915,
                    imageBackground = "https://media.rawg.io/media/games/995/9951d9d55323d08967640f7b9ab3e342.jpg"
                )
            ),
            GameStoreDetailInfo(
                id = 420,
                storeItem = StoreItem(
                    id = 4,
                    name = "App Store",
                    domain = "apps.apple.com",
                    gamesCount = 75588,
                    imageBackground = "https://media.rawg.io/media/games/0bd/0bd5646a3d8ee0ac3314bced91ea306d.jpg"
                )
            ),
            GameStoreDetailInfo(
                id = 39985,
                storeItem = StoreItem(
                    id = 8,
                    name = "Google Play",
                    domain = "play.google.com",
                    gamesCount = 17124,
                    imageBackground = "https://media.rawg.io/media/games/b4e/b4e4c73d5aa4ec66bbf75375c4847a2b.jpg"
                )
            )
        )
    }

    data class StoreItem(
        override val id: Int,
        override val name: String,
        val domain: String? = null,
        val gamesCount: Int = Int.ZERO,
        val imageBackground: String = String.EMPTY,
    ) : GameData
}
