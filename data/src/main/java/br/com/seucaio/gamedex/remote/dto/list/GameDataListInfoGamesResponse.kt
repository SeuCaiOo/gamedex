package br.com.seucaio.gamedex.remote.dto.list

import kotlinx.serialization.Serializable

@Serializable
data class GameDataListInfoGamesResponse(
    val id: Int,
    val name: String
)
