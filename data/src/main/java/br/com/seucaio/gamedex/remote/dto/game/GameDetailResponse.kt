package br.com.seucaio.gamedex.remote.dto.game

import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoGamesResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailResponse(
    val id: Int,
    val name: String,
    @SerialName("background_image")
    val imageBackground: String? = null,
    @SerialName("background_image_additional")
    val imageBackgroundAdditional: String? = null,
    val description: String,
    val metacritic: Int? = null,
    val released: String? = null,
    val website: String? = null,
    val rating: Double? = null,
    @SerialName("alternative_names")
    val alternativeNames: List<String>? = null,
    val platforms: List<PlatformInfoDetailResponse<GameDataListInfoResponse>>? = null,
    val stores: List<StoreInfoDetailResponse>? = null,
    val developers: List<GameDataListInfoResponse>? = null,
    val publishers: List<GameDataListInfoResponse>? = null,
    val genres: List<GameDataListInfoResponse>? = null,
    @SerialName("esrb_rating")
    val esrbRating: GameDataListInfoGamesResponse? = null,
)

@Serializable
data class PlatformInfoDetailResponse<T>(
    @SerialName("platform") val info: T,
    @SerialName("released_at")
    val releasedAt: String? = null,
    val requirements: PlatformRequirementsResponse? = null
) {
    @Serializable
    data class PlatformRequirementsResponse(
        val minimum: String? = null,
        val recommended: String? = null
    )
}

@Serializable
data class StoreInfoDetailResponse(
    val id: Int,
    @SerialName("store") val info: StoreItemResponse
) {
    @Serializable
    data class StoreItemResponse(
        val id: Int,
        val name: String,
        val domain: String? = null,
        @SerialName("games_count")
        val gamesCount: Int? = null,
        @SerialName("image_background")
        val imageBackground: String? = null,
    )
}
