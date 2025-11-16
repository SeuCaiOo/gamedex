package br.com.seucaio.gamedex.model.data

interface GameDataDetail {
    val id: Int
    val name: String
    val description: String
    val imageBackground: String?
    val gamesCount: Int
    val topGames: List<TopGameData>
}

