package br.com.seucaio.gamedex.remote.dto.game

import kotlinx.serialization.Serializable

@Serializable
data class GameScreenshotResponse(
    val id: Int,
    val image: String
)