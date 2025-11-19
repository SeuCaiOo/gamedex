package br.com.seucaio.gamedex.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDataInfoResponse(
    val id: Int,
    val name: String,
    val description: String,
    @SerialName("image_background")
    val imageBackground: String? = null,
    @SerialName("games_count")
    val gamesCount: Int,
)
