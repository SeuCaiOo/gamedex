package br.com.seucaio.gamedex.remote.dto.game

import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoGamesResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameItemResponse(
    val id: Int,
    val name: String,
    @SerialName("background_image")
    val imageBackground: String? = null,
    val metacritic: Int? = null,
    val released: String? = null,
    val rating: Double? = null,
    val platforms: List<PlatformInfoResponse<GameDataListInfoGamesResponse>>? = null,
    val stores: List<StoreInfoResponse<GameDataListInfoGamesResponse>>? = null,
    val genres: List<GameDataListInfoGamesResponse>? = null,
    @SerialName("esrb_rating")
    val esrbRating: GameDataListInfoGamesResponse? = null,
    @SerialName("short_screenshots")
    val shortScreenshots: List<GameScreenshotResponse>? = null,
) {
    @Serializable
    data class PlatformInfoResponse<T>(@SerialName("platform") val info: T)

    @Serializable
    data class StoreInfoResponse<T>(@SerialName("store") val info: T)
}
