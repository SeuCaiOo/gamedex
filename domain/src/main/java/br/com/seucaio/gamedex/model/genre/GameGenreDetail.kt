package br.com.seucaio.gamedex.model.genre

import br.com.seucaio.gamedex.model.data.GameDataDetail
import br.com.seucaio.gamedex.model.data.TopGameData

data class GameGenreDetail(
    override val id: Int,
    override val name: String,
    override val imageBackground: String? = null,
    val description: String,
    val gamesCount: Int,
    val topGames: List<TopGameData> = emptyList()
) : GameDataDetail {
    companion object {
        val sampleList = listOf(
            GameGenreDetail(
                id = 1,
                name = "Racing",
                description = """ 
                Racing games is the video game genre with a high focus on driving competition. 
                Such games are usually presented from the first-person or a third-person 
                perspective. It is noted that gamepads are generally more suited to control 
                the vehicle than keyboard/mouse pair. Although car avatars may render 
                real-life examples, there are many instances where spaceships, formless
                mechanisms and other fantastical entities take the role of the avatar. """.trimIndent(),
                gamesCount = 257626,
                topGames = TopGameData.sampleList
            ),
            GameGenreDetail(id = 2, name = "Action RPG", description = "", gamesCount = 150),
            GameGenreDetail(id = 3, name = "Adventure", description = "", gamesCount = 200),
            GameGenreDetail(id = 4, name = "Fighting", description = "", gamesCount = 80),
            GameGenreDetail(id = 5, name = "Platform", description = "", gamesCount = 100),
            GameGenreDetail(id = 6, name = "Shooter", description = "", gamesCount = 130),
            GameGenreDetail(id = 7, name = "Simulation", description = "", gamesCount = 50),
            GameGenreDetail(id = 8, name = "Sports", description = "", gamesCount = 60),
            GameGenreDetail(id = 9, name = "Strategy", description = "", gamesCount = 120)
        )
    }
}