package br.com.seucaio.gamedex.model.platform

import br.com.seucaio.gamedex.model.data.GameDataDetail
import br.com.seucaio.gamedex.model.data.TopGameData

data class GamePlatformDetail(
    override val id: Int,
    override val name: String,
    override val description: String,
    override val imageBackground: String? = null,
    override val gamesCount: Int,
    override val topGames: List<TopGameData> = TopGameData.sampleList
) : GameDataDetail {
    companion object {
        val sampleList = listOf(
            GamePlatformDetail(
                id = 1,
                name = "Xbox One",
                description = """
                Xbox One is a home video game console released by Microsoft in 2013. 
                Unlike its main competitor — PlayStation 4, the accent in the 
                development was made in multitasking and using a console as a home 
                media player to watch TV, listen to music and play the games. There is three 
                hardware option available to gamers: original console, One S version 
                (with a smaller body, no external power supply, and native HDR/4k support) 
                and One X (pro-gamer-oriented system with updated hardware and 4k 
                rendering in real time).""".trimIndent(),
                gamesCount = 130
            ),
            GamePlatformDetail(
                id = 2,
                name = "Xbox Series X/S",
                description = "",
                gamesCount = 150
            ),
            GamePlatformDetail(id = 3, name = "PC", description = "", gamesCount = 200),
            GamePlatformDetail(id = 4, name = "Nintendo Switch", description = "", gamesCount = 80),
            GamePlatformDetail(id = 5, name = "PlayStation 4", description = "", gamesCount = 100),
            GamePlatformDetail(id = 6, name = "PlayStation 5", description = "", gamesCount = 0),
            GamePlatformDetail(id = 7, name = "iOS", description = "", gamesCount = 50),
            GamePlatformDetail(id = 8, name = "Android", description = "", gamesCount = 60),
            GamePlatformDetail(id = 9, name = "PlayStation 2", description = "", gamesCount = 120)
        )
    }
}