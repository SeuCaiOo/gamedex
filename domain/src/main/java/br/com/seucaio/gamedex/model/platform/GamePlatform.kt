package br.com.seucaio.gamedex.model.platform

import br.com.seucaio.gamedex.model.data.GameData
import br.com.seucaio.gamedex.util.extension.ZERO

data class GamePlatform(
    override val id: Int,
    override val name: String,
    val gamesCount: Int = Int.ZERO
) : GameData {
    companion object {
        val sampleList = listOf(
            GamePlatform(id = 1, name = "Xbox One", gamesCount = 130),
            GamePlatform(id = 2, name = "Xbox Series X/S", gamesCount = 150),
            GamePlatform(id = 3, name = "PC", gamesCount = 200),
            GamePlatform(id = 4, name = "Nintendo Switch", gamesCount = 80),
            GamePlatform(id = 5, name = "PlayStation 4", gamesCount = 100),
            GamePlatform(id = 6, name = "PlayStation 5", gamesCount = 0),
            GamePlatform(id = 7, name = "iOS", gamesCount = 50),
            GamePlatform(id = 8, name = "Android", gamesCount = 60),
            GamePlatform(id = 9, name = "PlayStation 2", gamesCount = 120)
        )
    }
}
