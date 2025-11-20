package br.com.seucaio.gamedex.model.game

import br.com.seucaio.gamedex.model.data.GameDataDetail
import br.com.seucaio.gamedex.util.extension.EMPTY

data class GameDeveloperDetail(
    override val id: Int,
    override val name: String,
    override val imageBackground: String = String.EMPTY,
    val gamesCount: Int
) : GameDataDetail {
    companion object {
        val sampleList = listOf(
            GameDeveloperDetail(
                id = 3524,
                name = "Rockstar North",
                imageBackground = "https://media.rawg.io/media/screenshots/43b/43b00286439d859eaea32b8e269b83f9.jpg",
                gamesCount = 29
            ),
            GameDeveloperDetail(
                id = 10436,
                name = "War Drum Studios",
                imageBackground = "https://media.rawg.io/media/screenshots/878/878193558aca97b9d07d38cee9e16fd4.jpg",
                gamesCount = 6
            )
        )
    }
}
