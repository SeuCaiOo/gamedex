package br.com.seucaio.gamedex.remote.dto

import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoResponse
import kotlinx.serialization.Serializable

@Serializable
data class GameDataListResponse(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<GameDataListInfoResponse> = emptyList()
)