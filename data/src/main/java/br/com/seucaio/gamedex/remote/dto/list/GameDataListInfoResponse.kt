package br.com.seucaio.gamedex.remote.dto.list

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class GameDataListInfoResponse(
    val id: Int,
    val name: String,
    @SerialName("games_count")
    val gamesCount: Int,
    @SerialName("image_background")
    val imageBackground: String? = null,
    @EncodeDefault
    val games: List<GameDataListInfoGamesResponse>? = emptyList()
)