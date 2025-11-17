package br.com.seucaio.gamedex.model.genre

import br.com.seucaio.gamedex.model.data.GameData
import br.com.seucaio.gamedex.util.extension.ZERO

data class GameGenre(
    override val id: Int,
    override val name: String,
    val gamesCount: Int = Int.ZERO
) : GameData {
    companion object {
        val sampleList = listOf(
            GameGenre(id = 1, name = "Racing", gamesCount = 257626),
            GameGenre(id = 2, name = "Action RPG", gamesCount = 150),
            GameGenre(id = 3, name = "Adventure", gamesCount = 200),
            GameGenre(id = 4, name = "Fighting", gamesCount = 80),
            GameGenre(id = 5, name = "Platform", gamesCount = 100),
            GameGenre(id = 6, name = "Shooter", gamesCount = 130),
            GameGenre(id = 7, name = "Simulation", gamesCount = 50),
            GameGenre(id = 8, name = "Sports", gamesCount = 60),
            GameGenre(id = 9, name = "Strategy", gamesCount = 120)
        )
    }
}