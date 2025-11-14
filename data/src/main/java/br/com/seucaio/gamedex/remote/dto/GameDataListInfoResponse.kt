package br.com.seucaio.gamedex.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDataListInfoResponse(
    val id: Int,
    val name: String,
    @SerialName("games_count")
    val gamesCount: Int,
    @SerialName("image_background")
    val imageBackground: String? = null,
    val games: List<Pair<Int, String>> = emptyList()
)